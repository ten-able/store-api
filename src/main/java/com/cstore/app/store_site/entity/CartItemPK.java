package com.cstore.app.store_site.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartItemPK implements Serializable{
	
	@Column(name="cart_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer cartId;

	@Column(name="store_product_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer storeProductId;
	
	
	public CartItemPK(){
		
	}


	public Integer getCartId() {
		return cartId;
	}


	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public Integer getStoreProductId() {
		return storeProductId;
	}


	public void setStoreProductId(Integer storeProductId) {
		this.storeProductId = storeProductId;
	}
	
	

}
