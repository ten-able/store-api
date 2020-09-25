package org.cstore.app.store_site.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.StoreProduct;


@Repository
public class StoreProductRepository {
	
	
	@Autowired
	EntityManager entityManager;
	
	public StoreProduct save(StoreProduct storeProduct) {
		storeProduct.setStatus("ACTIVE");
		storeProduct.setCreatedBy("ADMIN");
		entityManager.persist(storeProduct);
		return storeProduct;
	}
	
	
	
	public Optional<StoreProduct> findById(Long storeProductId) {
		return Optional.of((StoreProduct)entityManager.createQuery("from StoreProduct where storeProductId=?1").setParameter(1, storeProductId).getSingleResult());
	}
	
	public List<StoreProduct> findAll() {
		return (List<StoreProduct>)entityManager.createQuery("from StoreProduct").getResultList();
	}
	
	public boolean deleteStore(Long storeProductId) {
		int updatedRecords = entityManager.createQuery("update StoreProduct set status='DELETED'  where storeProductId=?").setParameter(1, storeProductId).executeUpdate();
		return updatedRecords>0 ? true :  false;
	}
	
	public List<StoreProduct> findProductsByStore(Long storeId){
		return (List<StoreProduct>)entityManager.createQuery("from StoreProduct where store=?").setParameter(1, storeId).getResultList();

	}



	

}
