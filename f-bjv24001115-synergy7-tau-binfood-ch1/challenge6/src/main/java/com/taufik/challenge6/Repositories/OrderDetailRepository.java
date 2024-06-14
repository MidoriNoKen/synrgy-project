package com.taufik.challenge6.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taufik.challenge6.Models.Entities.Product;
import com.taufik.challenge6.Models.Entities.Order;
import com.taufik.challenge6.Models.Entities.OrderDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
    List<OrderDetail> findByOrder(Order order);

    List<OrderDetail> findByProduct(Product product);

    Optional<OrderDetail> findByOrderAndProductAndSize(Order order, Product product, String size);
}
