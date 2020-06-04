package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the store_product database table.
 * 
 */
@Entity
@Table(name="store_product")
@NamedQuery(name="StoreProduct.findAll", query="SELECT s FROM StoreProduct s")
public class StoreProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_product_id", unique=true, nullable=false)
	private Long storeProductId;

	@Column(name="created_by", length=250)
	private String createdBy;

	@Column(name="created_on")
	private Timestamp createdOn;

	@Column(name="quantity_available")
	private Integer quantityAvailable;

	@Column(length=50)
	private String status;

	@Column(name="updated_by", length=250)
	private String updatedBy;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	//bi-directional many-to-one association to CartItem
	@OneToMany(mappedBy="storeProduct")
	private Set<CartItem> cartItems;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="storeProduct")
	private Set<OrderItem> orderItems;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	//bi-directional many-to-one association to Store
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	public StoreProduct() {
	}

	public Long getStoreProductId() {
		return this.storeProductId;
	}

	public void setStoreProductId(Long storeProductId) {
		this.storeProductId = storeProductId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public CartItem addCartItem(CartItem cartItem) {
		getCartItems().add(cartItem);
		cartItem.setStoreProduct(this);

		return cartItem;
	}

	public CartItem removeCartItem(CartItem cartItem) {
		getCartItems().remove(cartItem);
		cartItem.setStoreProduct(null);

		return cartItem;
	}

	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setStoreProduct(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setStoreProduct(null);

		return orderItem;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}