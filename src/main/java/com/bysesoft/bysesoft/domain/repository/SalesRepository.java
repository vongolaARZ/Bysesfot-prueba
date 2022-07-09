package com.bysesoft.bysesoft.domain.repository;

import com.bysesoft.bysesoft.domain.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Long> {
}
