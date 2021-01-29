package com.cstore.app.store_site.dao;

import lombok.Data;

@Data
public class PaymentDao {
	public double total;
	public double tax;
	public String currency;
}
