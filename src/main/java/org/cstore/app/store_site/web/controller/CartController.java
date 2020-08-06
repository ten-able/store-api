package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.cstore.app.store_site.service.CartService;
import org.cstore.app.store_site.service.CustomerService;
import org.cstore.app.store_site.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cstore.app.store_site.dao.CartDao;
import com.cstore.app.store_site.dao.CartItemDao;
import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.CartItemPK;
import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreCart;
import com.cstore.app.store_site.entity.StoreProduct;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/carts")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
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
		
		return cartService.createStoreCart(cart);
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
	
	@GetMapping("/{cartId}/cartitems")
	public Set<CartItem> findCartItemsById(@PathVariable Long cartId) {
		return cartService.findCartItemsByCartId(cartId);
	}
	
	@GetMapping("")
	public List<StoreCart> findAll(){
		return  cartService.findAll();
	}
	
	@PostMapping("/{cartId}/cartitems")
	@ResponseStatus(code = HttpStatus.OK)
	public StoreCart addCartItems(@PathVariable Long cartId, @RequestBody CartItemDao itemDao) {
		
		CartItem cartItem  = new CartItem();
		CartItemPK pk = new CartItemPK();
		pk.setCartId(cartId);
		StoreProduct productId = storeService.findStoreProductById(itemDao.getProductId());
		pk.setStoreProductId(productId.getStoreProductId());
		cartItem.setId(pk);
		cartItem.setItemPrice(itemDao.itemPrice);
		cartItem.setQuantity(itemDao.getQuantity());
		//Need to add a condition to verify  the store id and store product id are valid
		return cartService.saveCartWithCartItems(cartId, itemDao.getStoreId(), cartItem);
	}

}
