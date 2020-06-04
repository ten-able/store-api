package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order_items database table.
 * 
 */
@Embeddable
public class OrderItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer orderId;

	@Column(name="store_product_id", insertable=false, updatable=false, unique=true, nullable=false)
	private Integer storeProductId;

	public OrderItemPK() {
	}
	public Integer getOrderId() {
		return this.orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStoreProductId() {
		return this.storeProductId;
	}
	public void setStoreProductId(Integer storeProductId) {
		this.storeProductId = storeProductId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderItemPK)) {
			return false;
		}
		OrderItemPK castOther = (OrderItemPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.storeProductId.equals(castOther.storeProductId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.storeProductId.hashCode();
		
		return hash;
	}
}