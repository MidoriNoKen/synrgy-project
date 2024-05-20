package com.taufik.challenge5.Repository;

import com.taufik.challenge5.Model.Entity.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
