package org.cstore.app.store_site.service;

import java.util.List;
import java.util.Optional;

import org.cstore.app.store_site.entity.Product;
import org.cstore.app.store_site.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
	
	private final ProductRepository prodRep;
	
	@Autowired
	public ProductService(ProductRepository prodRep) {
		this.prodRep = prodRep;
	}
	
	public Optional<Product> getProductById(Long id){
		return prodRep.findById(id);
	}
	
	public List<Product> getAllProducts(){
		return prodRep.findAll();
	}
	
	public Product createProduct(Product prod) {
		return prodRep.save(prod);
	}
	
	public Product updateProduct(Product prod) {
		return prodRep.save(prod);
	}
	
	public void deleteProduct(Long productId) {
		prodRep.deleteById(productId);
	}

}
