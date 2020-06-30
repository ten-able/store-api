package org.cstore.app.store_site.repo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.StoreCart;

@Repository
public class CartRepository {

	
	@Autowired
	EntityManager entityManager;
	
	public StoreCart save(StoreCart cart){

		entityManager.persist(cart);
		return cart;
	}
	
	public List<StoreCart> findCartByCustomerId(Long customerId) {
		return (List<StoreCart>) entityManager.createQuery("from StoreCart where customer=? and status='ACTIVE'").setParameter(1, customerId).getResultList();
	}
	
	public Optional<StoreCart> findById(Long cartId) {
		return (Optional<StoreCart>) entityManager.createQuery("from StoreCart where cartId=? and status='ACTIVE'").setParameter(1, cartId).getSingleResult();
	}
	
	public List<StoreCart>  findByStoreId(Long storeId) {
		return (List<StoreCart> ) entityManager.createQuery("from StoreCart where store=? and status='ACTIVE'").setParameter(1, storeId).getResultList();
	}
	
	public List<StoreCart> findAll(){
		return (List<StoreCart>) entityManager.createQuery("from StoreCart where status='ACTIVE'").getResultList();
	}
	
	public StoreCart cartPurchased(StoreCart cart) {
		cart.setStatus("PURCHASED");
		entityManager.persist(cart);
		return cart;
	}
	
	
	
}
