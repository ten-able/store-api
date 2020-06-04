package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the store_user database table.
 * 
 */
@Entity
@Table(name="store_user")
@NamedQuery(name="StoreUser.findAll", query="SELECT s FROM StoreUser s")
public class StoreUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_user_id", unique=true, nullable=false)
	private Long storeUserId;

	@Column(length=100)
	private String department;

	@Column(name="grant_date", nullable=false)
	private Timestamp grantDate;

	@Column(length=50)
	private String status;

	//bi-directional many-to-one association to ARole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", nullable=false)
	private ARole ARole;

	//bi-directional many-to-one association to AUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private AUser AUser;

	//bi-directional many-to-one association to Store
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	public StoreUser() {
	}

	public Long getStoreUserId() {
		return this.storeUserId;
	}

	public void setStoreUserId(Long storeUserId) {
		this.storeUserId = storeUserId;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Timestamp getGrantDate() {
		return this.grantDate;
	}

	public void setGrantDate(Timestamp grantDate) {
		this.grantDate = grantDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ARole getARole() {
		return this.ARole;
	}

	public void setARole(ARole ARole) {
		this.ARole = ARole;
	}

	public AUser getAUser() {
		return this.AUser;
	}

	public void setAUser(AUser AUser) {
		this.AUser = AUser;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}