package com.taufik.challenge5.Repository;

import com.taufik.challenge5.Model.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);

    List<Order> findByMerchantCodeAndDateBetween(Long merchantCode, LocalDateTime startDate, LocalDateTime endDate);

}
