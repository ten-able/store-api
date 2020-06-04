package org.cstore.app.store_site.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.squareup.square.SquareClient;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;
import com.squareup.square.models.GetPaymentResponse;
import com.squareup.square.models.Payment;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private SquareClient sClient;
	
	public PaymentService() {
		
	}

	/**
	 * sandbox-sq0idb-hsAaO02WZEiiFayItAge-w
	 * EAAAEGc_m_bWhXkRQVRJFjzUDCWyYx70BxSTXRblwP5ts7nXMIiJEL7GNJU0wHhz
	 * @throws IOException 
	 * @throws ApiException 
	 */
	public Payment createPayment(CreatePaymentRequest request) throws ApiException, IOException {

		
		PaymentsApi paymentsApi = sClient.getPaymentsApi();
		
		CreatePaymentResponse payResponse = paymentsApi.createPayment(request);
		
		Payment payment = payResponse.getPayment();
		System.out.println(payment.getReceiptNumber());
		System.out.println(payment.getReceiptNumber());
		return payment;
	}
	
	public Payment listPayment(String paymentId) throws ApiException, IOException {
		PaymentsApi paymentsApi = sClient.getPaymentsApi();
		GetPaymentResponse payment = paymentsApi.getPayment(paymentId);
		return payment.getPayment();
	}

}
