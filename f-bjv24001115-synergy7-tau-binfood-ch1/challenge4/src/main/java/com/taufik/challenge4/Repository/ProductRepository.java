package com.taufik.challenge4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taufik.challenge4.Model.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
