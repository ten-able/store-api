package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Base64;
import java.util.List;

import org.cstore.app.store_site.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.cstore.app.store_site.dao.SearchStoreDAO;
import com.cstore.app.store_site.dao.StoreProductDao;
import com.cstore.app.store_site.entity.Product;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreProduct;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/stores")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	
	@GetMapping("/{storeId}")
	public Store findStoreById(@PathVariable Long storeId){
		return storeService.findStoreById(storeId);
	}
	
	@PostMapping("")
	public Store createStore(@RequestBody Store store) {
		return storeService.createStore(store);
	}
	
	@GetMapping("")
	public List<Store> findAll(){
		return storeService.findAll();
	}
	
	@DeleteMapping("/{storeId}")
	public void deleteStore(Long  storeId) {
		storeService.deleteStore(storeId);
	}
	
	@PostMapping("/{storeId}/products")
	@ResponseStatus(CREATED)
	public void addStoreProduct(@RequestBody Product product, @PathVariable Long storeId) {
		StoreProduct storeProduct = new  StoreProduct();
		storeProduct.setProduct(product);
		Store store = new Store();
		store.setStoreId(storeId);
		storeProduct.setStore(store);
		storeProduct.setQuantityAvailable(10);
		storeService.createStoreProduct(storeProduct);
	}
	
	@PutMapping("/{storeId}/products")
	@ResponseStatus(CREATED)
	public void updateStoreProduct(@RequestBody StoreProductDao storeProductDao, @PathVariable Long storeId) {
		StoreProduct storeProduct = storeService.findStoreProductById(storeProductDao.getStoreProductId());
		storeProduct.setStatus(storeProductDao.getStatus());
		storeProduct.setQuantityAvailable(storeProductDao.getQuantity());
		storeService.updateStoreProduct(storeProduct);
	}
	
	@GetMapping("/{storeId}/products")
	public List<StoreProduct> findStoreProductsByStoreId(@PathVariable Long storeId) {
		return storeService.findStoreProductByStoreId(storeId);
	}
	
	@PostMapping("/search")
	public List<Store> searchStores(@RequestBody SearchStoreDAO searchDao){
		List<Store> searchResults = storeService.searchStoresByZip(searchDao);
		return searchResults;
		
	}
}
