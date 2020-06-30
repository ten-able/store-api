package org.cstore.app.store_site.repo;



import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.CartItemPK;

@Repository
public class CartItemsRepository {
	
	@Autowired
	EntityManager entityManager;
	
	
	public CartItem save(CartItem item) {
		item.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entityManager.persist(item);
		return item;
	}
	
	public List<CartItem> findCartItemsByCartId(Long cartId){
		return  (List<CartItem>) entityManager.createQuery("from CartItem where storeCart=?").setParameter(1, cartId).getResultList();
		
	}

}
