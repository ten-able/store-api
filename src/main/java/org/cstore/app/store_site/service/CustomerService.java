package org.cstore.app.store_site.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cstore.app.store_site.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.exception.InvalidUserException;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository custRepo;
	
	

	
	public List<Customer> findAll(){
		return custRepo.findAll();
	}
	
	public Customer findByUsername(String userName) {
		Customer customer = new Customer();
		customer.setCustUname(userName);
		return custRepo.findOne(Example.of(customer)).get();
				
	}
	
	public Customer createCustomer(Customer customer) {
		customer.setCreatedOn(new  Timestamp(Calendar.getInstance().getTimeInMillis()));
		return custRepo.save(customer);
	}

	public Customer findById(Long customerId) {
		return custRepo.findById(customerId).get();
	}

	public Customer updateCustomer(Customer customer) {
		return custRepo.save(customer);
	}
	
	public Optional<Customer> authenticate(String  userName, String pwd) {
		Customer cust = new Customer();
		cust.setCustUname(userName);
		Optional<Customer> customer = custRepo.findOne(Example.of(cust));
		return customer;
	}
	

}
