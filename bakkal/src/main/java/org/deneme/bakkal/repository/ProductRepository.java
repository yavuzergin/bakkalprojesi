package org.deneme.bakkal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.deneme.bakkal.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
