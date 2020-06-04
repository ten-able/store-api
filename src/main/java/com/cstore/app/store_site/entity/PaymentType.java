package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the payment_type database table.
 * 
 */
@Entity
@Table(name="payment_type")
@NamedQuery(name="PaymentType.findAll", query="SELECT p FROM PaymentType p")
public class PaymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="paytype_id", unique=true, nullable=false)
	private Integer paytypeId;

	@Column(length=50)
	private String status;

	@Column(name="vendor_id", length=2147483647)
	private String vendorId;

	@Column(name="vendor_key", length=2147483647)
	private String vendorKey;

	@Column(name="vendor_name", length=2147483647)
	private String vendorName;

	//bi-directional many-to-one association to CustomerPayment
	@OneToMany(mappedBy="paymentType")
	private Set<CustomerPayment> customerPayments;

	public PaymentType() {
	}

	public Integer getPaytypeId() {
		return this.paytypeId;
	}

	public void setPaytypeId(Integer paytypeId) {
		this.paytypeId = paytypeId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorKey() {
		return this.vendorKey;
	}

	public void setVendorKey(String vendorKey) {
		this.vendorKey = vendorKey;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Set<CustomerPayment> getCustomerPayments() {
		return this.customerPayments;
	}

	public void setCustomerPayments(Set<CustomerPayment> customerPayments) {
		this.customerPayments = customerPayments;
	}

	public CustomerPayment addCustomerPayment(CustomerPayment customerPayment) {
		getCustomerPayments().add(customerPayment);
		customerPayment.setPaymentType(this);

		return customerPayment;
	}

	public CustomerPayment removeCustomerPayment(CustomerPayment customerPayment) {
		getCustomerPayments().remove(customerPayment);
		customerPayment.setPaymentType(null);

		return customerPayment;
	}

}