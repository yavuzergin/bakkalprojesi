package org.deneme.bakkal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.deneme.bakkal.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
