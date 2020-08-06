package com.cstore.app.store_site.dao;

import lombok.Data;

@Data
public class CartItemDao {
	
	public Long storeId;
	public Integer quantity;
	public double itemPrice;
	public Long productId;

}
