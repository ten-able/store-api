package org.cstore.app.store_site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cstore.app.store_site.entity.ARole;

public interface RoleRepository extends JpaRepository<ARole, Long> {

}
