package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

import org.cstore.app.store_site.entity.Product;
import org.cstore.app.store_site.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("")
	public List<Product> getAllProducts(){
		log.info("process=getAllProducts");
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(Long prodId){
		 log.info("process=getProductById, product id={}",prodId);
		 Optional<Product> product = productService.getProductById(prodId);
		 return product.map(p->ResponseEntity.ok(p))
		 		.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("")
    @ResponseStatus(CREATED)
    public Product createProduct(@RequestBody Product product) {
        log.info("process=create-product, product name={}", product.getProduct_name());
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        log.info("process=update-product, product_id={}", id);
        product.setProduct_id(id);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("process=delete-product, product_id={}", id);
        productService.deleteProduct(id);
    }

}
