package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRolePK id;

	@Column(name="grant_date")
	private Timestamp grantDate;

	//bi-directional many-to-one association to ARole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private ARole ARole;

	//bi-directional many-to-one association to AUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false, insertable=false, updatable=false)
	private AUser AUser;

	public UserRole() {
	}

	public UserRolePK getId() {
		return this.id;
	}

	public void setId(UserRolePK id) {
		this.id = id;
	}

	public Timestamp getGrantDate() {
		return this.grantDate;
	}

	public void setGrantDate(Timestamp grantDate) {
		this.grantDate = grantDate;
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

}