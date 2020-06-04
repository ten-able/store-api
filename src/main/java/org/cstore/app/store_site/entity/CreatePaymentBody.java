package org.cstore.app.store_site.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

public class CreatePaymentBody {

	@SerializedName("items")
    Object[] items;

    @SerializedName("currency")
    String currency;

    public Object[] getItems() {
        return items;
    }

    public String getCurrency() {
        return currency;
    }
}
