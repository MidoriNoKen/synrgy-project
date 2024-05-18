package com.taufik.challenge4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taufik.challenge4.Model.Entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
