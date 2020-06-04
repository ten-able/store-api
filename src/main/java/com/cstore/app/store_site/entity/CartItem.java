package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cart_items database table.
 * 
 */
@Entity
@Table(name="cart_items")
@NamedQuery(name="CartItem.findAll", query="SELECT c FROM CartItem c")
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private  CartItemPK id;

	@Column(name="created_on")
	private Timestamp createdOn;

	private Integer quantity;

	//bi-directional many-to-one association to StoreCart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cart_id", nullable=false,insertable=false, updatable=false)
	private StoreCart storeCart;

	//bi-directional many-to-one association to StoreProduct
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_product_id", nullable=false, insertable=false, updatable=false)
	private StoreProduct storeProduct;

	public CartItem() {
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public StoreCart getStoreCart() {
		return this.storeCart;
	}

	public void setStoreCart(StoreCart storeCart) {
		this.storeCart = storeCart;
	}

	public StoreProduct getStoreProduct() {
		return this.storeProduct;
	}

	public void setStoreProduct(StoreProduct storeProduct) {
		this.storeProduct = storeProduct;
	}

}