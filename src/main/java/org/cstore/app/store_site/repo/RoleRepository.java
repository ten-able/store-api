package org.cstore.app.store_site.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cstore.app.store_site.entity.ARole;

@Repository
public interface RoleRepository extends JpaRepository<ARole, Long> {

}
