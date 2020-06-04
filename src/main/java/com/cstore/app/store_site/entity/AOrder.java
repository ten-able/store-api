package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the a_order database table.
 * 
 */
@Entity
@Table(name="a_order")
@NamedQuery(name="AOrder.findAll", query="SELECT a FROM AOrder a")
public class AOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id", unique=true, nullable=false)
	private Integer orderId;

	@Column(name="cust_notes", length=250)
	private String custNotes;

	@Column(name="order_created_date", nullable=false)
	private Timestamp orderCreatedDate;

	@Column(name="order_status", length=50)
	private String orderStatus;

	@Column(name="order_status_updated_date", nullable=false)
	private Timestamp orderStatusUpdatedDate;

	@Column(name="order_total", nullable=false)
	private double orderTotal;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cust_id", nullable=false)
	private Customer customer;

	//bi-directional many-to-one association to Store
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="AOrder")
	private Set<OrderItem> orderItems;

	public AOrder() {
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCustNotes() {
		return this.custNotes;
	}

	public void setCustNotes(String custNotes) {
		this.custNotes = custNotes;
	}

	public Timestamp getOrderCreatedDate() {
		return this.orderCreatedDate;
	}

	public void setOrderCreatedDate(Timestamp orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOrderStatusUpdatedDate() {
		return this.orderStatusUpdatedDate;
	}

	public void setOrderStatusUpdatedDate(Timestamp orderStatusUpdatedDate) {
		this.orderStatusUpdatedDate = orderStatusUpdatedDate;
	}

	public double getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
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

	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setAOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setAOrder(null);

		return orderItem;
	}

}