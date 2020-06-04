//package org.cstore.app.store_site.entity;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.MapsId;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
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
//@Table(name = "user_role")
//public class UserRole {
//	
//	@EmbeddedId
//	UserRoleKey id;
//	
//	
//	@ManyToOne
//    @MapsId("user_id")
//    @JoinColumn(name = "user_id")
//    User user;
// 
//    @ManyToOne
//    @MapsId("role_id")
//    @JoinColumn(name = "role_id")
//    Role role;
//	
//	@JsonProperty("created_on")
//	@Column(name="grant_date")
//    private LocalDateTime grantDate;
//
//}
