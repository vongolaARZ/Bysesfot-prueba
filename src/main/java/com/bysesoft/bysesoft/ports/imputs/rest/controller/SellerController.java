package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Seller;
import com.bysesoft.bysesoft.domain.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerController{

    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        sellerService.create(seller);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Seller seller = sellerService.getSellerByName(name);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Seller seller = sellerService.findById(id);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<Seller> sellers = new ArrayList<>(sellerService.getAll());
        return new ResponseEntity<>(sellers,HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        sellerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
