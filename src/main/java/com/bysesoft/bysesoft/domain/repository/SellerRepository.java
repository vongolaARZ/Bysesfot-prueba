package com.bysesoft.bysesoft.domain.repository;

import com.bysesoft.bysesoft.domain.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByName(String name);
}
