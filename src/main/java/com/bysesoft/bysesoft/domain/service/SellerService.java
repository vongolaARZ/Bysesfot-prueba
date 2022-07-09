package com.bysesoft.bysesoft.domain.service;

import com.bysesoft.bysesoft.domain.model.Seller;

import java.util.List;

public interface SellerService {

    void create(Seller seller);
    Seller getSellerByName(String name);
    List<Seller> getAll();
    void deleteById(Long id);
    Seller findById(Long id);
}
