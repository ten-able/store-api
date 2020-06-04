package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the a_user database table.
 * 
 */
@Entity
@Table(name="a_user")
@NamedQuery(name="AUser.findAll", query="SELECT a FROM AUser a")
public class AUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Long userId;

	@Column(name="created_on", nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false, length=355)
	private String email;

	@Column(name="last_login")
	private Timestamp lastLogin;

	@Column(nullable=false, length=50)
	private String password;

	@Column(length=50)
	private String status;

	@Column(nullable=false, length=50)
	private String username;

	//bi-directional many-to-one association to StoreUser
	@OneToMany(mappedBy="AUser")
	private Set<StoreUser> storeUsers;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="AUser")
	private Set<UserRole> userRoles;

	public AUser() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<StoreUser> getStoreUsers() {
		return this.storeUsers;
	}

	public void setStoreUsers(Set<StoreUser> storeUsers) {
		this.storeUsers = storeUsers;
	}

	public StoreUser addStoreUser(StoreUser storeUser) {
		getStoreUsers().add(storeUser);
		storeUser.setAUser(this);

		return storeUser;
	}

	public StoreUser removeStoreUser(StoreUser storeUser) {
		getStoreUsers().remove(storeUser);
		storeUser.setAUser(null);

		return storeUser;
	}

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setAUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setAUser(null);

		return userRole;
	}

}