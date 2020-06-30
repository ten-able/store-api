package org.cstore.app.store_site.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.cstore.app.store_site.entity.RoleType;
import org.cstore.app.store_site.repo.RoleRepository;
import org.cstore.app.store_site.repo.UserRepository;
import org.cstore.app.store_site.repo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cstore.app.store_site.entity.ARole;
import com.cstore.app.store_site.entity.AUser;
import com.cstore.app.store_site.entity.UserRole;
import com.cstore.app.store_site.entity.UserRolePK;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRoleRepository userRoleRepo;

	public UserService() {
		// this.userRepository = userRepository;
	}

	public Optional<AUser> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public List<AUser> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<AUser> createUser(Optional<AUser> user, RoleType roleType) {
		Optional<AUser> aUser = Optional.of(userRepository.save(user.get()));
		ARole role = new ARole();
		role.setRoleName(roleType.name());
		ARole aRole = roleRepo.findOne(Example.of(role)).get();
		UserRole userRole = new UserRole();
		UserRolePK id = new UserRolePK();
		id.setRoleId(aRole.getRoleId());
		id.setUserId(aUser.get().getUserId());
		userRole.setId(id);
		userRole.setGrantDate(new  Timestamp(Calendar.getInstance().getTimeInMillis()));
		userRoleRepo.save(userRole);
		return aUser;

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
