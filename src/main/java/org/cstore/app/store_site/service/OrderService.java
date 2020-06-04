package org.cstore.app.store_site.service;

import java.util.List;

import org.cstore.app.store_site.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstore.app.store_site.entity.AOrder;


@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	public AOrder createOrder(AOrder order){
		return  orderRepo.save(order);
	}
	
	public AOrder findOrder(Long orderId) {
		return orderRepo.findById(orderId).get();
	}
	
	public List<AOrder> findAll(){
		return orderRepo.findAll();
	}
	
	public AOrder updateOrder(AOrder order) {
		return orderRepo.save(order);
	}

}
