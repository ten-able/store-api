package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the store_cart database table.
 * 
 */
@Entity
@Table(name="store_cart")
@NamedQuery(name="StoreCart.findAll", query="SELECT s FROM StoreCart s")
public class StoreCart implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id", unique=true, nullable=false)
	private Long cartId;

	@Column(name="created_on")
	private Timestamp createdOn;

	@Column(length=50)
	private String status;

	//bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy="storeCart")
	private Set<CartItem> cartItems;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cust_id", nullable=false)
	private Customer customer;

	//bi-directional many-to-one association to Store
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	public StoreCart() {
	}

	public Long getCartId() {
		return this.cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setStoreCart(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setStoreCart(null);

		return cartItem;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}