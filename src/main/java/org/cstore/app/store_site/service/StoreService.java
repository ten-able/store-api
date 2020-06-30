package org.cstore.app.store_site.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.cstore.app.store_site.repo.StoreProductRepository;
import org.cstore.app.store_site.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreProduct;

@Service
@Transactional
public class StoreService {
	
	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	StoreProductRepository storeProductRepo;
	
	public Store createStore(Store store){
		return storeRepo.save(store);
	}
	
	public Store findStoreById(Long storeId) {
		return storeRepo.findById(storeId).get();
	}
	
	public List<Store> findAll(){
		return storeRepo.findAll();
	}
	
	public Store updateStore(Store store) {
		return storeRepo.save(store);
	}
	
	
	public boolean deleteStore(Long  storeId) {
		return storeRepo.deleteStore(storeId);
	}
	
	public StoreProduct createStoreProduct(StoreProduct storeProduct) {
		storeProduct.setCreatedOn(new  Timestamp(Calendar.getInstance().getTimeInMillis()));
		return storeProductRepo.save(storeProduct);
	}
	
	public StoreProduct updateStoreProduct(StoreProduct storeProduct) {
		storeProduct.setUpdatedOn(new  Timestamp(Calendar.getInstance().getTimeInMillis()));
		return storeProductRepo.save(storeProduct);
	}
	
	public StoreProduct findStoreProductById(Long storeProductId) {
		return storeProductRepo.findById(storeProductId).get();
	}
	
	public List<StoreProduct> findStoreProductByStoreId(Long storeId) {
		return storeProductRepo.findProductsByStore(storeId);
	}


}
