package org.cstore.app.store_site.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.io.IOException;

import org.cstore.app.store_site.entity.CreatePaymentBody;
import org.cstore.app.store_site.entity.CreatePaymentResponse;
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

import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.Payment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payments")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
//	@Autowired
//	private Dotenv  dotEnv;
	
	
	
	
	@PostMapping("square")
    @ResponseStatus(CREATED)
    public ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentRequest paymentReq) {
        log.info("process=create-payment, payment amount={}",paymentReq.getAmountMoney().getAmount());
        try {
        	Payment payment = paymentService.createPayment(paymentReq);
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

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("USD").setAmount(new Long(calculateOrderAmount()))
                .build();
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = new PaymentIntent();
        try {
			intent = PaymentIntent.create(createParams);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.ok(new CreatePaymentResponse("pk_test_XdzU7CWZLSGKaWaZ3Y5z4h3y00qf9g35KH", intent.getClientSecret()));
    }
	
	int calculateOrderAmount() {
        // Replace this constant with a calculation of the order's amount
        // Calculate the order total on the server to prevent
        // users from directly manipulating the amount on the client
        return 1400;
    }

	
	
	
	

}
