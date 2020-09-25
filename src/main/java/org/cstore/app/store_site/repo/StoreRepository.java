package org.cstore.app.store_site.repo;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.cstore.app.store_site.dao.SearchStoreDAO;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreProduct;

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
		return Optional.of((Store)entityManager.createQuery("from Store where store_id=?1").setParameter(1, storeId).getSingleResult());
	}
	
	public List<Store> findAll() {
		return (List<Store>)entityManager.createQuery("from Store").getResultList();
	}
	
	public boolean deleteStore(Long storeId) {
		int updatedRecords = entityManager.createQuery("update store set status='DELETED'  where store_id=?1").executeUpdate();
		return updatedRecords>0 ? true :  false;
	}
	
	public List<Store> searchStores(SearchStoreDAO searchObj) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Store> query = cb.createQuery(Store.class);
		Root<Store> store = query.from(Store.class);
		
		Path<String> cityPath = store.get("city");
		Path<String> statePath = store.get("storeState");
		Path<String> namePath = store.get("storeName");
		
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(searchObj.getCity()))
		predicates.add(cb.like(cityPath, searchObj.getCity()));
		if(!StringUtils.isEmpty(searchObj.getState()))
		predicates.add(cb.like(statePath, searchObj.getState()));
		if(!StringUtils.isEmpty(searchObj.getName()))
		predicates.add(cb.like(namePath, searchObj.getName()));
		query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			
		//.where(cb.like(namePath, searchObj.getName()));
		
		return (List<Store>)entityManager.createQuery(query).getResultList();
		
		//No zip based search for now.
//		StringBuilder qb = new StringBuilder();
//		qb.append("SELECT * from Store where ");
//		if(!StringUtils.isEmpty(searchObj.getCity())) {
//			qb.append(" city like '%").append(searchObj.getCity()).append("%'");
//		}
//		if(StringUtils.isEmpty(searchObj.getState())) {
//			qb.append(" state like '%").append(searchObj.getState()).append("%'");
//		}
//		if(StringUtils.isEmpty(searchObj.getName())) {
//			qb.append(" store_name like '%").append(searchObj.getName()).append("%'");
//			qb.append(" short_name like '%").append(searchObj.getName()).append("%'");
//		}
//		
//		Query searchQuery = entityManager.createNativeQuery(qb.toString(), Store.class);
//		return (List<Store>)searchQuery.getResultList();
				
	}
	

	
	
	

}
