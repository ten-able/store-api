//package org.cstore.app.store_site.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
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
//@Table(name = "product")
//public class Product {
//
//	@Id
//	//@SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq")
//	//@GeneratedValue(generator = "product_id_generator")
//	private Long product_id;
//
//	@Column(nullable = false,name="product_name")
//	private String productName;
//	
//	@Column(nullable = false, name="ptype")
//	private String ptype;
//	
//	@Column(name="description")
//	private String description;
//	@Column(nullable = false)
//	private double price;
//	
//	@Column(nullable = false,name="quantityAvailable")
//	private Integer quantityAvailable;
//	
//	@Column(name="icon_url")
//	private String iconUrl;
//	
//	@Column(name="image_url")
//	private String imageUrl;
//	
//	@Column(name="brand")
//	private String brand;
//	
//
//}
