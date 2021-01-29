package com.cstore.app.store_site.dao;

import java.util.List;

import lombok.Data;

@Data
public class CartDao {
	
	Long customerId;
	Long storeId;
	Long cartId;
	Double total;
	String status;
	public PaymentDao payment;
	public List<CartItemDao> cartItems;
	

}
