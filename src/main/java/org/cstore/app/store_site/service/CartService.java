package org.cstore.app.store_site.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.cstore.app.store_site.repo.CartItemsRepository;
import org.cstore.app.store_site.repo.CartRepository;
import org.cstore.app.store_site.repo.CustomerRepository;
import org.cstore.app.store_site.repo.StoreProductRepository;
import org.cstore.app.store_site.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.cstore.app.store_site.dao.CartDao;
import com.cstore.app.store_site.dao.CartItemDao;
import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreCart;
import com.cstore.app.store_site.entity.StoreProduct;

@Service
@Transactional
public class CartService {
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	CartItemsRepository cartItemRepo;
	
	@Autowired
	StoreProductRepository storeProductRepo;
	
	
	
//	@Autowired
//	CartItemsRepository cartItemRepo;
	
	public StoreCart createStoreCart(CartDao cart){
		StoreCart storeCart = new StoreCart();
		if(cart.getCustomerId()!= null) {
			Optional<Customer> customer = customerRepo.findById(cart.getCustomerId());
			if(customer.isPresent())
			storeCart.setCustomer(customer.get());
		}
		if(cart.getStoreId() != null) {
			Optional<Store> store = storeRepo.findById(cart.getStoreId());
			if(store.isPresent())
			storeCart.setStore(store.get());
		}
		
		StoreCart updatedCart = cartRepo.findCartByCustomerIdAndStoreId(storeCart.getCustomer().getCustId(),storeCart.getStore().getStoreId());
		if(updatedCart != null) {
			return createCart(updatedCart);
		}else {
			return updatedCart;
		}
	}
	
	
	public StoreCart createCart(StoreCart  cart) {
		cart.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		cart.setStatus("ACTIVE");
		return  cartRepo.save(cart);
	}
	
	public StoreCart updateCart(StoreCart  cart) {
		cart.setStatus("ACTIVE");
		return  cartRepo.save(cart);
	}
	
	public StoreCart saveCartWithCartItems(Long cartId, Long storeId, CartItem item) {
		Optional<StoreCart> storeCart = cartRepo.findById(cartId);
		if(storeCart.isPresent()) {
			List<CartItem> dbCartItems = cartItemRepo.findCartItemsByCartId(cartId);
			//If cart is not empty, get the db items and compare if existing item or not.
			//If so update the quantity.
			if(!CollectionUtils.isEmpty(dbCartItems)) {
				CartItem dbCartItem = dbCartItems.stream()
						.filter(cItem -> cItem.getId().getStoreProductId().equals(item.getId().getStoreProductId())).findAny().orElse(null);
				if(dbCartItem != null) {
					dbCartItem.setQuantity(dbCartItem.getQuantity()+1);
					dbCartItem.setItemPrice(item.getItemPrice());
					cartItemRepo.update(dbCartItem);
				}else {
					cartItemRepo.save(item);
				}
			}{
				//Cart exists but no items..Rare scenario
				storeCart.get().addCartItem(item);
			}
			storeCart.get().setStore(storeCart.get().getStore());
			return storeCart.get();
		} else { 
			StoreCart newCart = new StoreCart();
			newCart.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			newCart.setStatus("ACTIVE");
			newCart.addCartItem(item);
			Store store = storeRepo.findById(storeId).get();
			newCart.setStore(store);
			return cartRepo.save(newCart);
		}
		
	}
	
	public StoreCart removeCartItemFromCart(Long cartId, CartItem item){
		List<CartItem> dbCartItems = cartItemRepo.findCartItemsByCartId(cartId);
		CartItem dbCartItem = dbCartItems.stream()
				.filter(cItem -> cItem.getId().getStoreProductId().equals(item.getId().getStoreProductId())).findAny().orElse(null);
		if(dbCartItem != null)
		cartItemRepo.deleteItem(dbCartItem);
		return findCartById(cartId);
	}
	
	public StoreCart updateCarItem(Long cartId, CartItem item) {
		Optional<StoreCart> storeCart = cartRepo.findById(cartId);
		if(storeCart.isPresent()) {
			List<CartItem> dbCartItems = cartItemRepo.findCartItemsByCartId(cartId);
			if(!CollectionUtils.isEmpty(dbCartItems)) {
				CartItem dbCartItem = dbCartItems.stream()
						.filter(cItem -> cItem.getId().getStoreProductId().equals(item.getId().getStoreProductId())).findAny().orElse(null);
				dbCartItem.setQuantity(item.getQuantity());
				cartItemRepo.update(dbCartItem);
			}
		}
		return findCartById(cartId);
	}
	
	public CartDao findCartByCustomerIdAndStoreId(Long customerId, Long storeId){
		StoreCart storeCart = cartRepo.findCartByCustomerIdAndStoreId(customerId, storeId);
		CartDao cartDao = mapStoreCart2CartDao(storeCart);
		List<CartItemDao> cartItems =new ArrayList<CartItemDao>();
		storeCart.getCartItems().forEach(item->{			
			cartItems.add(mapCartItem2CartItemDao(item));
		});
		cartDao.setCartItems(cartItems);
		return cartDao;
	}
	
	public List<StoreCart> findCartByCustomerId(Long customerId) {
		return cartRepo.findCartByCustomerId(customerId);
	}
	
	public List<StoreCart> findCartByStoreId(Long storeId) {
		return cartRepo.findByStoreId(storeId);
	}
	
	public StoreCart findCartById(Long cartId) {
		StoreCart storeCart = cartRepo.findById(cartId).get();
		storeCart.setCartItems(storeCart.getCartItems());
		return storeCart;
	}
	
	public StoreCart cartPurchased(Long cartId) {
		
		Optional<StoreCart> cart = cartRepo.findById(cartId);
		cart.get().setStatus("PURCHASED");
		return cartRepo.save(cart.get());
	}

	public List<StoreCart> findAll() {
		return cartRepo.findAll();
	}
	
	public Set<CartItem> findCartItemsByCartId(Long cartId){
		Optional<StoreCart> cart = cartRepo.findById(cartId);
		if(cart.isPresent())
			return cart.get().getCartItems();
		else
			return new HashSet<CartItem>();
	}
	
	private CartDao mapStoreCart2CartDao(StoreCart storeCart) {
		CartDao cartDao = new CartDao();
		cartDao.setCustomerId(storeCart.getCustomer().getCustId());
		cartDao.setStoreId(storeCart.getStore().getStoreId());
		cartDao.setCartId(storeCart.getCartId());
		cartDao.setStatus(storeCart.getStatus());
		return cartDao;
	}
	
	private CartItemDao mapCartItem2CartItemDao(CartItem item) {
		CartItemDao itemDao = new CartItemDao();
		
		itemDao.setItemPrice(item.getItemPrice());
		itemDao.setProductId(item.getStoreProduct().getStoreProductId());
		itemDao.setQuantity(item.getQuantity());
		itemDao.setProductUrl(item.getStoreProduct().getProduct().getImageUrl());
		itemDao.setIconUrl(item.getStoreProduct().getProduct().getIconUrl());
		itemDao.setProductName(item.getStoreProduct().getProduct().getProductName());
		return itemDao;
	}

}
