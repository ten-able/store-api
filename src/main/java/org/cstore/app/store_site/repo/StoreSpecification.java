package org.cstore.app.store_site.repo;

import java.text.MessageFormat;

import org.springframework.data.jpa.domain.Specification;

import com.cstore.app.store_site.entity.Store;

public final class StoreSpecification {
	public static Specification<Store> cityContains(String city) {
	    return (root, query, builder) -> builder.like(root.get("city"), contains(city));
	}
	
	public static Specification<Store> stateContains(String state) {
	    return (root, query, builder) -> builder.like(root.get("store_state"), contains(state));
	}
	
	public static Specification<Store> zipContains(String zip) {
	    return (root, query, builder) -> builder.like(root.get("city"), contains(zip));
	}
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}
}
