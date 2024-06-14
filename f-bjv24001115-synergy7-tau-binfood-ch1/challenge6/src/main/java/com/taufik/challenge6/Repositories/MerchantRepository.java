package com.taufik.challenge6.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taufik.challenge6.Models.Entities.Merchant;
import com.taufik.challenge6.Models.Entities.Auth.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findByOpen(boolean b);

    List<Merchant> findByOwner(User user);

    boolean existsByName(String name);
}
