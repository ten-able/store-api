//package org.cstore.app.store_site.entity;
//
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "a_role")
//public class Role {
//	
//	@Id
//	@GeneratedValue
//	 private Long role_id;
//	
//	@Column(nullable = false)
//	private String role_name;
//	
//	@OneToMany(mappedBy = "user")
//    Set<UserRole> users;
//
//}
