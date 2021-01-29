package org.cstore.app.store_site.repo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.entity.Store;
import com.cstore.app.store_site.entity.StoreCart;

@Repository
public class CartRepository {

	
	@Autowired
	EntityManager entityManager;
	
	public StoreCart save(StoreCart cart){

		entityManager.persist(cart);
		if(!CollectionUtils.isEmpty(cart.getCartItems()))
		cart.getCartItems().forEach(item -> {
			entityManager.persist(item);
		});
		return cart;
	}
	
	
	public StoreCart findCartByCustomerIdAndStoreId(Long customerId,Long storeId) {
		Customer customer = new Customer();
		customer.setCustId(customerId);
		Store store = new Store();
		store.setStoreId(storeId);
		return (StoreCart) entityManager.createQuery("from StoreCart where customer=?1 and  store=?2 and status='ACTIVE'").setParameter(1, customer).setParameter(2, store).getSingleResult();
	}
	
	public List<StoreCart> findCartByCustomerId(Long customerId) {
		Customer customer = new Customer();
		customer.setCustId(customerId);
		return (List<StoreCart>) entityManager.createQuery("from StoreCart where customer=?1 and status='ACTIVE'").setParameter(1, customer).getResultList();
	}
	
	public Optional<StoreCart> findById(Long cartId) {
		return Optional.of((StoreCart)entityManager.createQuery("from StoreCart where cartId=?1 and status='ACTIVE'").setParameter(1, cartId).getSingleResult());
	}
	
	public List<StoreCart>  findByStoreId(Long storeId) {
		return (List<StoreCart> ) entityManager.createQuery("from StoreCart where store=?1 and status='ACTIVE'").setParameter(1, storeId).getResultList();
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
