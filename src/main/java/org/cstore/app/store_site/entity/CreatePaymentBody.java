package org.cstore.app.store_site.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;


@Data
public class CreatePaymentBody {


	
	Long userId;
	Long cartId;

    @SerializedName("currency")
    String currency;

  
}
