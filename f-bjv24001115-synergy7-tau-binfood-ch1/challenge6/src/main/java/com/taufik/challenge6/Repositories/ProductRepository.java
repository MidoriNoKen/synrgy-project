package com.taufik.challenge6.Repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taufik.challenge6.Models.Entities.Product;
import com.taufik.challenge6.Models.Entities.Merchant;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

        @Query(value = "SELECT SUM(od.price) " +
                        "FROM order_detail od " +
                        "WHERE od.menu_item_id = :productId " +
                        "AND od.deleted = false", nativeQuery = true)
        Integer getTotalPrice(@Param("productId") UUID productId);

        @Query(value = "SELECT SUM(od.price) " +
                        "FROM order_detail od " +
                        "JOIN orders o ON o.id = od.order_id " +
                        "WHERE od.menu_item_id = :productId " +
                        "AND od.deleted = false " +
                        "AND o.order_time > :startTime " +
                        "AND o.order_time < :endTime", nativeQuery = true)
        Integer getTotalPrice(@Param("productId") UUID productId, @Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime);

        @Query(value = "SELECT SUM(od.qty) " +
                        "FROM order_detail od " +
                        "WHERE od.menu_item_id = :productId " +
                        "AND od.deleted = false", nativeQuery = true)
        Integer getTotalQty(@Param("productId") UUID productId);

        @Query(value = "SELECT SUM(od.qty) " +
                        "FROM order_detail od " +
                        "JOIN orders o ON o.id = od.order_id " +
                        "WHERE od.menu_item_id = :productId " +
                        "AND od.deleted = false " +
                        "AND o.order_time > :startTime " +
                        "AND o.order_time < :endTime", nativeQuery = true)
        Integer getTotalQty(@Param("productId") UUID productId, @Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime);

        boolean existsByMerchantAndName(Merchant merchant, String name);

        boolean existsByName(String name);

        List<Product> findByMerchant(Merchant merchant);

        List<Product> findByMerchant(Merchant merchant, Pageable pageable);
}
