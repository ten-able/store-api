package org.cstore.app.store_site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.squareup.square.Environment;
import com.squareup.square.SquareClient;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class ApiConfig {
	
	@Bean("SquareClient")
	public SquareClient getSquareClient(){
		SquareClient square = new SquareClient.Builder().environment(Environment.SANDBOX)
				.accessToken("EAAAEGc_m_bWhXkRQVRJFjzUDCWyYx70BxSTXRblwP5ts7nXMIiJEL7GNJU0wHhz").build();
		
		return square;
	}
	
//	@Bean("dotEnv")
//	public Dotenv getDotEnv() {
//		Dotenv load = Dotenv.load();
//		return load;
//	}

}
