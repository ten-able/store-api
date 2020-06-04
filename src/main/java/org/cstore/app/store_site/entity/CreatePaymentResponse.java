package org.cstore.app.store_site.entity;

import lombok.Data;

@Data
public class CreatePaymentResponse {

	private String publishableKey;
    private String clientSecret;

    public CreatePaymentResponse(String publishableKey, String clientSecret) {
        this.publishableKey = publishableKey;
        this.clientSecret = clientSecret;
    }
}
