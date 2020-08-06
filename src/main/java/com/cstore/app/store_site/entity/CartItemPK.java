package com.cstore.app.store_site.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartItemPK implements Serializable{
	
	@Column(name="cart_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Long cartId;

	@Column(name="store_product_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Long storeProductId;
	
	
	public CartItemPK(){
		
	}


	public Long getCartId() {
		return cartId;
	}


	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}


	public Long getStoreProductId() {
		return storeProductId;
	}


	public void setStoreProductId(Long storeProductId) {
		this.storeProductId = storeProductId;
	}
	
	

}
