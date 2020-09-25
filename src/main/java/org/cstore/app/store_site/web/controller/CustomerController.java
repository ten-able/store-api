package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

import org.cstore.app.store_site.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cstore.app.store_site.dao.CustomerLoginDao;
import com.cstore.app.store_site.dao.CustomerRegisterDao;
import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.exception.InvalidUserException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
/**
 * 
 * @author nagadheeranreddyseelam
 * Need to implement check for username availability
 * Need to implement recaptcha
 * Need to implement confirm email for registration
 * 
 *
 */
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("")
	@ResponseStatus(CREATED)
	public Customer createCustomer(@RequestBody CustomerRegisterDao customerRegDao) {
		Customer customer = new Customer();
		customer.setCustEmail(customerRegDao.getEmail());
		customer.setCustUname(customerRegDao.getUsername());
		customer.setCustPwd(customerRegDao.getPassword());
		return customerService.createCustomer(customer);
	}

	@PostMapping("/authenticate")
	@ResponseStatus(CREATED)
	public ResponseEntity<Customer> authenticate(@RequestBody CustomerLoginDao customerDao) {
		Optional<Customer> user = customerService.authenticate(customerDao.getUsername(), customerDao.getPassword());
		return user.map(u -> ResponseEntity.ok(u)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("")
	public List<Customer> findAll() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public Customer findCustomerById(@PathVariable Long customerId) {
		return customerService.findById(customerId).get();
	}

	@GetMapping("/{userName}")
	public Customer findCustomerByName(@PathVariable String userName) {
		return customerService.findByUsername(userName);
	}

	@PutMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer);
	}

	@PostMapping("/register")
	@ResponseStatus(code=HttpStatus.OK)
	public Customer registerCustomer(@RequestBody CustomerRegisterDao  customerDao) {
		//@TODO - Add full registration here. Temporarily creating user directly
		//Need to add registration table. once user created, user will be in NOTCONFIRMED state.
		//Check for duplicate username/email
		//email sent to user
		//user will click on email to confirm and then user will be in ACTIVE state.
		Customer customer = new Customer();
		customer.setCustEmail(customerDao.getEmail());
		customer.setCustUname(customerDao.getUsername());
		customer.setCustPwd(customerDao.getPassword());
		
		 return customerService.createCustomer(customer);
	}

}
