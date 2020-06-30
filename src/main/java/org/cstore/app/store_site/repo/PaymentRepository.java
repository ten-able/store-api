package org.cstore.app.store_site.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.CustomerPayment;
import com.cstore.app.store_site.entity.PaymentType;

@Repository
public class PaymentRepository {
	
	@Autowired
	EntityManager entityManager;
	
	public CustomerPayment createPayment(CustomerPayment customerPayment) {
		 customerPayment.setStatus("INITIATED");
		 entityManager.persist(customerPayment);
		 return customerPayment;
	}
	
	public CustomerPayment updatePayment(CustomerPayment customerPayment, String status) {
		 customerPayment.setStatus(status);
		 entityManager.persist(customerPayment);
		 return customerPayment;
	}
	
	public Optional<CustomerPayment> findPaymentById(Long paymentId) {
		return (Optional<CustomerPayment>)entityManager.createQuery("from CustomerPayment where paymentId=?").setParameter(1, paymentId).getSingleResult();
	}
	
	public List<CustomerPayment> findPaymentsByOrderId(Long orderId){
		return entityManager.createQuery("from CustomerPayment where orderId=?").setParameter(1, orderId).getResultList();

	}
	
	public Optional<PaymentType> loadPaymentTypeByName(String payVendorName) {
		return (Optional<PaymentType>)entityManager.createQuery("from PaymentType where vendorName=?").setParameter(1, payVendorName).getSingleResult();

	}

}
