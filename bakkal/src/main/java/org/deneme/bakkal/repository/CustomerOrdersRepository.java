package org.deneme.bakkal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.deneme.bakkal.model.CustomerOrders;

import java.util.List;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders,Long> {
    List<CustomerOrders> findByCustomer_Id(Long customerId);
    List<CustomerOrders> findByProduct_Id(Long productId);
    @Query("select sum(order.total_cost) from CustomerOrders order where order.customer.id = :customerId")
    double retrieveDebthOfCustomer(@Param("customerId") Long customerId);
}