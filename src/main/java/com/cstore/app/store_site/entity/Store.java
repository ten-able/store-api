package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@Table(name="store")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_id", unique=true, nullable=false)
	private Long storeId;

	@Column(length=250)
	private String address1;

	@Column(length=250)
	private String address2;

	@Column(length=200)
	private String city;

	@Column(name="created_by", length=250)
	private String createdBy;

	@Column(name="created_on")
	private Timestamp createdOn;

	@Column(length=15)
	private String phone;

	@Column(name="short_name", length=250)
	private String shortName;

	@Column(name="store_name", length=250)
	private String storeName;

	@Column(name="store_state", length=200)
	private String storeState;

	@Column(length=100)
	private String type;

	@Column(name="updated_by", length=250)
	private String updatedBy;

	@Column(name="updated_on")
	private Timestamp updatedOn;

	@Column(length=10)
	private String zip;
	
	@Column(length=10)
	private String status;

	//bi-directional many-to-one association to AOrder
	@OneToMany(mappedBy="store")
	private Set<AOrder> AOrders;

	//bi-directional many-to-one association to StoreCart
	@OneToMany(mappedBy="store")
	private Set<StoreCart> storeCarts;

	//bi-directional many-to-one association to StoreProduct
	@OneToMany(mappedBy="store",fetch = FetchType.LAZY)
	private Set<StoreProduct> storeProducts;

	//bi-directional many-to-one association to StoreUser
	@OneToMany(mappedBy="store",fetch = FetchType.LAZY)
	private Set<StoreUser> storeUsers;

	public Store() {
	}

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreState() {
		return this.storeState;
	}

	public void setStoreState(String storeState) {
		this.storeState = storeState;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Set<AOrder> getAOrders() {
		return this.AOrders;
	}

	public void setAOrders(Set<AOrder> AOrders) {
		this.AOrders = AOrders;
	}

	public AOrder addAOrder(AOrder AOrder) {
		getAOrders().add(AOrder);
		AOrder.setStore(this);

		return AOrder;
	}

	public AOrder removeAOrder(AOrder AOrder) {
		getAOrders().remove(AOrder);
		AOrder.setStore(null);

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
		storeCart.setStore(this);

		return storeCart;
	}

	public StoreCart removeStoreCart(StoreCart storeCart) {
		getStoreCarts().remove(storeCart);
		storeCart.setStore(null);

		return storeCart;
	}

	public Set<StoreProduct> getStoreProducts() {
		return this.storeProducts;
	}

	public void setStoreProducts(Set<StoreProduct> storeProducts) {
		this.storeProducts = storeProducts;
	}

	public StoreProduct addStoreProduct(StoreProduct storeProduct) {
		getStoreProducts().add(storeProduct);
		storeProduct.setStore(this);

		return storeProduct;
	}

	public StoreProduct removeStoreProduct(StoreProduct storeProduct) {
		getStoreProducts().remove(storeProduct);
		storeProduct.setStore(null);

		return storeProduct;
	}

	public Set<StoreUser> getStoreUsers() {
		return this.storeUsers;
	}

	public void setStoreUsers(Set<StoreUser> storeUsers) {
		this.storeUsers = storeUsers;
	}

	public StoreUser addStoreUser(StoreUser storeUser) {
		getStoreUsers().add(storeUser);
		storeUser.setStore(this);

		return storeUser;
	}

	public StoreUser removeStoreUser(StoreUser storeUser) {
		getStoreUsers().remove(storeUser);
		storeUser.setStore(null);

		return storeUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}