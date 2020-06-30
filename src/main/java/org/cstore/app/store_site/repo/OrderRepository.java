package org.cstore.app.store_site.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		return (Optional<AOrder>)entityManager.createQuery("from AOrder where order_id=?").setParameter(1, orderId).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<AOrder> findAll() {
		return (List<AOrder>)entityManager.createQuery("from AOrder").getResultList();
	}
	
	public AOrder findOrderByCustomerId(Long customerId) {
		Query query = entityManager.createNamedQuery("from AOrder where customer=?");
		query.setParameter(1, customerId);
		return (AOrder) query.getSingleResult();
	}
	

	

}
