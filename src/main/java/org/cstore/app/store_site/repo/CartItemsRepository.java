package org.cstore.app.store_site.repo;



import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.CartItem;
import com.cstore.app.store_site.entity.CartItemPK;
import com.cstore.app.store_site.entity.StoreCart;

@Repository
public class CartItemsRepository {
	
	@Autowired
	EntityManager entityManager;
	
	
	public CartItem save(CartItem item) {
		item.setCreatedOn(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		entityManager.persist(item);
		return item;
	}
	
	public CartItem update(CartItem item) {
		entityManager.merge(item);
		return item;
	}
	
	public List<CartItem> findCartItemsByCartId(Long cartId){
		StoreCart cart = new StoreCart();
		cart.setCartId(cartId);
		return  (List<CartItem>) entityManager.createQuery("from CartItem where storeCart=?1").setParameter(1, cart).getResultList();
		
	}

	public void deleteItem(CartItem item) {
		entityManager.remove(item);
	}
}
