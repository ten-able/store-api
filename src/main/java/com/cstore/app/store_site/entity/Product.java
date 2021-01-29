package com.cstore.app.store_site.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id", unique=true, nullable=false)
	private Long productId;

	@Column(length=2147483647)
	private String brand;

	@Column(length=2147483647)
	private String description;

	@Column(name="icon_url", length=2147483647)
	private String iconUrl;

	@Column(name="image_url", length=2147483647)
	private String imageUrl;

	@Column(nullable=false)
	private double price;

	@Column(name="product_name", nullable=false, length=250)
	private String productName;

	@Column(nullable=false, length=200)
	private String ptype;

	@Column(name="quantity_available")
	private Integer quantityAvailable;

	//bi-directional many-to-one association to StoreProduct
//	@OneToMany(mappedBy="product",fetch = FetchType.LAZY)
//	private Set<StoreProduct> storeProducts;

	public Product() {
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public Integer getQuantityAvailable() {
		return this.quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

//	public Set<StoreProduct> getStoreProducts() {
//		return this.storeProducts;
//	}
//
//	public void setStoreProducts(Set<StoreProduct> storeProducts) {
//		this.storeProducts = storeProducts;
//	}

//	public StoreProduct addStoreProduct(StoreProduct storeProduct) {
//		getStoreProducts().add(storeProduct);
//		storeProduct.setProduct(this);
//
//		return storeProduct;
//	}
//
//	public StoreProduct removeStoreProduct(StoreProduct storeProduct) {
//		getStoreProducts().remove(storeProduct);
//		storeProduct.setProduct(null);
//
//		return storeProduct;
//	}

}