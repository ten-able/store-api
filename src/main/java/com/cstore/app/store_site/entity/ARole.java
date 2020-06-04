package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the a_role database table.
 * 
 */
@Entity
@Table(name="a_role")
@NamedQuery(name="ARole.findAll", query="SELECT a FROM ARole a")
public class ARole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", unique=true, nullable=false)
	private Integer roleId;

	@Column(name="role_name", nullable=false, length=255)
	private String roleName;

	//bi-directional many-to-one association to StoreUser
	@OneToMany(mappedBy="ARole")
	private Set<StoreUser> storeUsers;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="ARole")
	private Set<UserRole> userRoles;

	public ARole() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<StoreUser> getStoreUsers() {
		return this.storeUsers;
	}

	public void setStoreUsers(Set<StoreUser> storeUsers) {
		this.storeUsers = storeUsers;
	}

	public StoreUser addStoreUser(StoreUser storeUser) {
		getStoreUsers().add(storeUser);
		storeUser.setARole(this);

		return storeUser;
	}

	public StoreUser removeStoreUser(StoreUser storeUser) {
		getStoreUsers().remove(storeUser);
		storeUser.setARole(null);

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
		userRole.setARole(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setARole(null);

		return userRole;
	}

}