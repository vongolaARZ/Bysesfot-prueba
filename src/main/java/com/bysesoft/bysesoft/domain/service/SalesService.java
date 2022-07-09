package com.bysesoft.bysesoft.domain.service;

import com.bysesoft.bysesoft.domain.model.Sales;

import java.util.List;

public interface SalesService {

    void create(Sales sales);
    Sales findById(Long id);
    List<Sales> findAll();
}
