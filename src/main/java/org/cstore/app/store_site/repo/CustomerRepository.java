package org.cstore.app.store_site.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cstore.app.store_site.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
