//package org.cstore.app.store_site.entity;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "a_user")
//public class User {
//    @Id
//    @GeneratedValue
//    @Column(name="user_id")
//    private Long userId;
//
//    @Column(nullable = false,name = "username")
//    private String username;
//
//    @Column(nullable = false, unique = true, name="email")
//    private String email;
//
//    @JsonProperty("created_on")
//    private LocalDateTime createdOn;
//
//    @JsonProperty("last_login")
//    private LocalDateTime lastLogin;
//    
//    
//    @OneToMany(mappedBy="role")
//    private Set<UserRole> roles = new HashSet<>();
//
//    @PrePersist
//    void preSave() {
//        if(createdOn == null) {
//        	createdOn = LocalDateTime.now();
//        }
//    }
//
//    @PreUpdate
//    void preUpdate() {
//        if(lastLogin == null) {
//        	lastLogin = LocalDateTime.now();
//        }
//    }
//    
//    @Column(name="status")
//    private String status;
//}
