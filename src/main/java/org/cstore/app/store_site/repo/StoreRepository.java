package org.cstore.app.store_site.repo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.Store;

@Repository
public class StoreRepository {
	
	@Autowired
	EntityManager entityManager;
	
	public Store save(Store store) {
		store.setStatus("ACTIVE");
		store.setCreatedOn(new  Timestamp(Calendar.getInstance().getTimeInMillis()));
		store.setUpdatedOn(new  Timestamp(Calendar.getInstance().getTimeInMillis()));
		store.setCreatedBy("ADMIN");
		entityManager.persist(store);
		return store;
	}
	
	public Optional<Store> findById(Long storeId) {
		return (Optional<Store>)entityManager.createQuery("from Store where store_id=?").setParameter(1, storeId).getSingleResult();
	}
	
	public List<Store> findAll() {
		return (List<Store>)entityManager.createQuery("from Store").getResultList();
	}
	
	public boolean deleteStore(Long storeId) {
		int updatedRecords = entityManager.createQuery("update store set status='DELETED'  where store_id=?").executeUpdate();
		return updatedRecords>0 ? true :  false;
	}
	
	
	

}
