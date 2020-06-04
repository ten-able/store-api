package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cust_id", unique=true, nullable=false)
	private Long custId;

	@Column(name="created_on", nullable=false)
	private Timestamp createdOn;

	@Column(name="cust_email", nullable=false, length=355)
	private String custEmail;

	@Column(name="cust_pwd", nullable=false, length=50)
	private String custPwd;

	@Column(name="cust_uname", nullable=false, length=50)
	private String custUname;

	@Column(name="last_login")
	private Timestamp lastLogin;

	//bi-directional many-to-one association to AOrder
	@OneToMany(mappedBy="customer")
	private Set<AOrder> AOrders;

	//bi-directional many-to-one association to StoreCart
	@OneToMany(mappedBy="customer")
	private Set<StoreCart> storeCarts;

	public Customer() {
	}

	public Long getCustId() {
		return this.custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPwd() {
		return this.custPwd;
	}

	public void setCustPwd(String custPwd) {
		this.custPwd = custPwd;
	}

	public String getCustUname() {
		return this.custUname;
	}

	public void setCustUname(String custUname) {
		this.custUname = custUname;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Set<AOrder> getAOrders() {
		return this.AOrders;
	}

	public void setAOrders(Set<AOrder> AOrders) {
		this.AOrders = AOrders;
	}

	public AOrder addAOrder(AOrder AOrder) {
		getAOrders().add(AOrder);
		AOrder.setCustomer(this);

		return AOrder;
	}

	public AOrder removeAOrder(AOrder AOrder) {
		getAOrders().remove(AOrder);
		AOrder.setCustomer(null);

		return AOrder;
	}

	public Set<StoreCart> getStoreCarts() {
		return this.storeCarts;
	}

	public void setStoreCarts(Set<StoreCart> storeCarts) {
		this.storeCarts = storeCarts;
	}

	public StoreCart addStoreCart(StoreCart storeCart) {
		getStoreCarts().add(storeCart);
		storeCart.setCustomer(this);

		return storeCart;
	}

	public StoreCart removeStoreCart(StoreCart storeCart) {
		getStoreCarts().remove(storeCart);
		storeCart.setCustomer(null);

		return storeCart;
	}

}