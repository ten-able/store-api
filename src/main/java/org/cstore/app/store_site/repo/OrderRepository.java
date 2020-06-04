package org.cstore.app.store_site.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.AOrder;

@Repository
public class OrderRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	public OrderRepository() {
		
	}
	
	public AOrder save(AOrder order) {
		entityManager.persist(order);
		return order;
	}

	public Optional<AOrder> findById(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AOrder> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
