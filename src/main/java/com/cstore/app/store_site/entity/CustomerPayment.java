package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the customer_payment database table.
 * 
 */
@Entity
@Table(name="customer_payment")
@NamedQuery(name="CustomerPayment.findAll", query="SELECT c FROM CustomerPayment c")
public class CustomerPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payment_id", unique=true, nullable=false)
	private Long paymentId;

	@Column(precision=131089)
	private BigDecimal amount;

	@Column(name="order_id", nullable=false)
	private Long orderId;

	@Column(length=2147483647)
	private String status;

	@Column(name="vendor_txn_id", length=2147483647)
	private String vendorTxnId;
	
	@Column(name="pay_intent_id")
	private String payIntentId;

	//bi-directional many-to-one association to PaymentType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pay_type_id", nullable=false)
	private PaymentType paymentType;

	public CustomerPayment() {
	}

	public Long getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendorTxnId() {
		return this.vendorTxnId;
	}

	public void setVendorTxnId(String vendorTxnId) {
		this.vendorTxnId = vendorTxnId;
	}

	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getPayIntentId() {
		return payIntentId;
	}

	public void setPayIntentId(String payIntentId) {
		this.payIntentId = payIntentId;
	}
	
	

}