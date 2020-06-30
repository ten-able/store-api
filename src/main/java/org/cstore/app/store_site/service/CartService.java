package org.cstore.app.store_site.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cstore.app.store_site.repo.CartItemsRepository;
import org.cstore.app.store_site.repo.CartRepository;
import org.cstore.app.store_site.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreCart;

@Service
@Transactional
public class CartService {
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	StoreRepository storeRepo;
	
//	@Autowired
//	CartItemsRepository cartItemRepo;
	
	
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
		return cartRepo.findById(cartId).get();
	}
	
	public StoreCart cartPurchased(Long cartId) {
		
		Optional<StoreCart> cart = cartRepo.findById(cartId);
		cart.get().setStatus("PURCHASED");
		return cartRepo.save(cart.get());
	}

	public List<StoreCart> findAll() {
		return cartRepo.findAll();
	}

}
