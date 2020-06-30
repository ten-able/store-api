package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.io.IOException;

import org.cstore.app.store_site.entity.CreatePaymentBody;
import org.cstore.app.store_site.entity.CreatePaymentResponse;
import org.cstore.app.store_site.service.CartService;
import org.cstore.app.store_site.service.CustomerService;
import org.cstore.app.store_site.service.OrderService;
import org.cstore.app.store_site.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cstore.app.store_site.entity.CustomerPayment;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.Payment;
import com.stripe.Stripe;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payments")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	
	@PostMapping("square")
    @ResponseStatus(CREATED)
    public ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentRequest paymentReq) {
        log.info("process=create-payment, payment amount={}",paymentReq.getAmountMoney().getAmount());
        try {
        	Payment payment = paymentService.createSquarePayment(paymentReq);
			return ResponseEntity.ok().body(payment); 
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
    }
	
	
	@GetMapping("/square/{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable String paymentId){
		try {
			
			return ResponseEntity.ok().body(paymentService.listPayment(paymentId));
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("stripe")
    @ResponseStatus(CREATED)
    public ResponseEntity<CreatePaymentResponse> createPaymentIntent(@RequestBody CreatePaymentBody paymentReq) {
		Stripe.apiKey = "sk_test_4fcX34aiRas28yb1lwSJRl2c00FQXI37XB";
        log.info("process=create-payment, payment amount={}",paymentReq);
        
        
        CustomerPayment customerPayment = paymentService.createStripePayment(paymentReq);
        
		 return ResponseEntity.ok(new
		 CreatePaymentResponse(customerPayment.getPaymentType().getVendorKey(),
		 customerPayment.getPayIntentId()));
		 
			// return ResponseEntity.ok(new
			// CreatePaymentResponse("pk_test_XdzU7CWZLSGKaWaZ3Y5z4h3y00qf9g35KH",
			// intent.getClientSecret()));
      }
	
	


	
	
	
	

}
