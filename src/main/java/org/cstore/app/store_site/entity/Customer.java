//package org.cstore.app.store_site.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//@Table(name="Customer")
//public class Customer {
//	
//	@Id
//	@Column(name="cust_id",unique = true)
//	private Long customerId;
//	
//	@Column(name="cust_uname",unique = true)
//	private String uname;
//	
//	@Column(name="cust_pwd")
//    private String custPwd;
//	
//	@Column(name="cust_email")
//    private String custEmail;
//	
//	@Column(name="created_on")
//    private LocalDateTime createdOn;
//	
//	@Column(name="last_login")
//    private LocalDateTime lastLogin;
//
//}
