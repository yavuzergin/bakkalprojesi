package org.deneme.bakkal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.deneme.bakkal.model.CustomerOrders;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders,Long> {
}