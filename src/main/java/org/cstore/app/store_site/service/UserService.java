package org.cstore.app.store_site.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cstore.app.store_site.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cstore.app.store_site.entity.AUser;

@Service
@Transactional
public class UserService {
	@Autowired
    private UserRepository userRepository;

    
    public UserService() {
       // this.userRepository = userRepository;
    }

    public Optional<AUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<AUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<AUser> createUser(Optional<AUser> user) {
        return Optional.of(userRepository.save(user.get()));
    }

    public Optional<AUser> updateUser(Optional<AUser> user) {
        return Optional.of(userRepository.save(user.get()));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    public void addUserRole(Long userId, Long roleId) {
    	
    }
}
