package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the order_items database table.
 * 
 */
@Entity
@Table(name="order_items")
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id;

	@Column(name="created_on")
	private Timestamp createdOn;

	private Integer quantity;

	//bi-directional many-to-one association to AOrder
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false, insertable=false, updatable=false)
	private AOrder AOrder;

	//bi-directional many-to-one association to StoreProduct
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_product_id", nullable=false, insertable=false, updatable=false)
	private StoreProduct storeProduct;

	public OrderItem() {
	}

	public OrderItemPK getId() {
		return this.id;
	}

	public void setId(OrderItemPK id) {
		this.id = id;
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

	public AOrder getAOrder() {
		return this.AOrder;
	}

	public void setAOrder(AOrder AOrder) {
		this.AOrder = AOrder;
	}

	public StoreProduct getStoreProduct() {
		return this.storeProduct;
	}

	public void setStoreProduct(StoreProduct storeProduct) {
		this.storeProduct = storeProduct;
	}

}