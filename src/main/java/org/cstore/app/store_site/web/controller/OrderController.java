package org.cstore.app.store_site.web.controller;

import java.util.List;

import org.cstore.app.store_site.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cstore.app.store_site.entity.AOrder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("")
	public AOrder createOrder(@RequestBody AOrder order) {
		return orderService.createOrder(order);
	}
	
	@GetMapping("/{id}")
	public AOrder findOrder(@PathVariable Long orderId) {
		return orderService.findOrder(orderId);
	}
	
	@GetMapping("")
	public List<AOrder> findOrders(){
		return orderService.findAll();
	}
	
	@GetMapping("/{customerId}")
	public AOrder findOrderByCustomerId(@PathVariable Long customerId) {
		return orderService.findOrderByCustomerId(customerId);
	}
	
	@PutMapping
	public AOrder updateOrder(@RequestBody AOrder order) {
		return orderService.updateOrder(order);
	}
	

}
