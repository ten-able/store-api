package org.cstore.app.store_site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.UserRole;

@Repository
public interface UserRoleRepository  extends JpaRepository<UserRole, Long> {

}
