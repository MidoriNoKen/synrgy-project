package com.taufik.challenge5.Repository;

import com.taufik.challenge5.Model.Entity.Merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    java.util.List<Merchant> findByOpen(boolean open);
}
