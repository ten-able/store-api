package org.cstore.app.store_site.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.AUser;

@Repository
public class UserRepository {

	@Autowired
	private EntityManager entityManager;

	public UserRepository() {

	}

	public AUser save(AUser user) {
		user.setStatus("ACTIVE");
		entityManager.persist(user);
		return user;
	}

	public Optional<AUser> findById(Long id) {
		AUser AUser = entityManager.find(AUser.class, id);
		return AUser != null ? Optional.of(AUser) : Optional.empty();
	}
	
	public Optional<AUser> findByAUsername(String AUserName){
		return (Optional<AUser>) entityManager.createQuery("from AUser where AUserName=? and  where status='ACTIVE'").setParameter(1, AUserName).getSingleResult();
		
	}
	
	public void deleteById(Long id) {
		AUser AUser = entityManager.find(AUser.class,id);
		AUser.setStatus("INACTIVE");
//		AUser.getRoles().forEach(role->{
//			
//		});
		entityManager.persist(AUser);
	}
	
	public List<AUser> findAll() {
        return entityManager.createQuery("from AUser where status='ACTIVE'").getResultList();
    }
	
	public List<AUser> findAllById(List<Long> AUserIds) {
        return entityManager.createQuery("from AUser where status='ACTIVE'").getResultList();
    }
	
	
	public void deleteAll(List<AUser> AUsers) {
		AUsers.forEach(AUser->{
			AUser.setStatus("INACTIVE");
			entityManager.persist(AUser);
		});
	
	}
	

}
