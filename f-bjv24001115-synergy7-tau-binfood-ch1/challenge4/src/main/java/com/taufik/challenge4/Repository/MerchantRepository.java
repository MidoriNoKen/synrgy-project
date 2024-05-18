package com.taufik.challenge4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taufik.challenge4.Model.Entity.Merchant;

import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    List<Merchant> findByOpen(boolean open);
}
