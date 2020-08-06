package org.cstore.app.store_site.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.cstore.app.store_site.repo.CartItemsRepository;
import org.cstore.app.store_site.repo.CartRepository;
import org.cstore.app.store_site.repo.CustomerRepository;
import org.cstore.app.store_site.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cstore.app.store_site.dao.CartDao;
import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreCart;

@Service
@Transactional
public class CartService {
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	
	
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
		return createCart(storeCart);
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
		Store store = storeRepo.findById(storeId).get();
		if(storeCart.isPresent()) {
			storeCart.get().addCartItem(item);
			storeCart.get().setStore(store);
			return cartRepo.save(storeCart.get());
		} else {
			StoreCart newCart = new StoreCart();
			newCart.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			newCart.setStatus("ACTIVE");
			newCart.addCartItem(item);
			newCart.setStore(store);
			return cartRepo.save(newCart);
		}
		
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

}
