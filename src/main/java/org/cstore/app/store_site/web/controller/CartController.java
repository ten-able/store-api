package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.cstore.app.store_site.service.CartService;
import org.cstore.app.store_site.service.CustomerService;
import org.cstore.app.store_site.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cstore.app.store_site.dao.CartDao;
import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreCart;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/cart")
@Slf4j
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	StoreService storeService;
	
	
	@PostMapping("")
    @ResponseStatus(CREATED)
	public StoreCart createStoreCart(@RequestBody CartDao cart){
		StoreCart storeCart = new StoreCart();
		if(cart.getCustomerId()!= null) {
			Customer customer = customerService.findById(cart.getCustomerId());
			storeCart.setCustomer(customer);
		}
		if(cart.getStoreId() != null) {
			Store store = storeService.findStoreById(cart.getStoreId());
			storeCart.setStore(store);
		}
		return cartService.createCart(storeCart);
	}
	
	@GetMapping("/customer/{customerId}")
	public List<StoreCart> findCartByCustomerId(@PathVariable Long customerId){
		return cartService.findCartByCustomerId(customerId);
	}
	
	@GetMapping("/store/{storeId}")
	public List<StoreCart> findCartByStored(@PathVariable Long storeId){
		return cartService.findCartByStoreId(storeId);
	}
	
	
	@GetMapping("/{cartId}")
	public StoreCart findCartById(@PathVariable Long cartId) {
		return cartService.findCartById(cartId);
	}
	
	@GetMapping("")
	public List<StoreCart> findAll(){
		return  cartService.findAll();
	}
	
	@PutMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public StoreCart addCartItems(Long cartId, Long storeId, CartItem cartItem) {
		return cartService.saveCartWithCartItems(cartId, storeId, cartItem);
	}

}
