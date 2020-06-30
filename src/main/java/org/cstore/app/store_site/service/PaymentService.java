package org.cstore.app.store_site.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.cstore.app.store_site.entity.CreatePaymentBody;
import org.cstore.app.store_site.repo.CartRepository;
import org.cstore.app.store_site.repo.CustomerRepository;
import org.cstore.app.store_site.repo.OrderRepository;
import org.cstore.app.store_site.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstore.app.store_site.entity.AOrder;
import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.Customer;
import com.cstore.app.store_site.entity.CustomerPayment;
import com.cstore.app.store_site.entity.OrderItem;
import com.cstore.app.store_site.entity.PaymentType;
import com.cstore.app.store_site.entity.StoreCart;
import com.squareup.square.SquareClient;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;
import com.squareup.square.models.GetPaymentResponse;
import com.squareup.square.models.Payment;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
@Transactional
public class PaymentService {

	@Autowired
	private SquareClient sClient;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderRepository orderRepo;

	public PaymentService() {

	}

	/**
	 * sandbox-sq0idb-hsAaO02WZEiiFayItAge-w
	 * EAAAEGc_m_bWhXkRQVRJFjzUDCWyYx70BxSTXRblwP5ts7nXMIiJEL7GNJU0wHhz
	 * 
	 * @throws IOException
	 * @throws ApiException
	 */
	public Payment createSquarePayment(CreatePaymentRequest request) throws ApiException, IOException {

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

	public CustomerPayment createStripePayment(CreatePaymentBody paymentReq) {
		StoreCart storeCart = cartRepo.findById(paymentReq.getCartId()).get();

		Long amount = calculateOrderAmount(storeCart);
		PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder().setCurrency("USD")
				.setAmount(amount).build();

		AOrder order = new AOrder();
		Set<OrderItem> orderItems = new HashSet<>();
		for (CartItem cartItem : storeCart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setAOrder(order);
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setItemPrice(cartItem.getItemPrice());
		}
		Customer customer = customerRepo.findById(paymentReq.getUserId()).get();
		order.setCustomer(customer);
		order.setOrderItems(orderItems);
		order.setOrderStatus("INITIATED");
		order.setOrderTotal(amount);

		orderRepo.save(order);

		CustomerPayment dbPay = new CustomerPayment();
		dbPay.setAmount(new BigDecimal(amount));
		dbPay.setOrderId((long)order.getOrderId());
		dbPay.setStatus("INITIATED");

		PaymentType payType = paymentRepo.loadPaymentTypeByName("Stripe").get();
		dbPay.setPaymentType(payType);
		
		//Call Stripe payment Intent
		PaymentIntent intent = new PaymentIntent();
		try {
			intent = PaymentIntent.create(createParams);
		} catch (StripeException e) {
			e.printStackTrace();
			System.out.println("Error creating intent...");
			return null;
		}

		dbPay.setPayIntentId(intent.getId());
		paymentRepo.createPayment(dbPay);

		return dbPay;
	}

	private Long calculateOrderAmount(StoreCart storeCart) {
		Double total = 0d;
		for(CartItem item : storeCart.getCartItems()) {
			total+=item.getItemPrice() * item.getQuantity();
		}
		return total.longValue();
	}
	
	public AOrder completePayment(Long cartId, Long paymentTxnId, String vendorTxnId) {
		//save vendor tx
		//updaate cart as purchased
		//update order as Placed
		//Return status and message
		//TODO convert paymentId to some form of uuid
		CustomerPayment customerPay = paymentRepo.findPaymentById(paymentTxnId).get();
		customerPay.setVendorTxnId(vendorTxnId);
		Optional<AOrder> order = orderRepo.findById(customerPay.getOrderId());
		order.get().setOrderStatus("PAY_RECEIVED");
		Optional<StoreCart> storeCart = cartRepo.findById(cartId);
		storeCart.get().setStatus("PURCHASED");
		
		paymentRepo.updatePayment(customerPay, "PAY_SUCCESS");
		orderRepo.save(order.get());
		cartRepo.save(storeCart.get());
		
		return order.get();
	}
}
