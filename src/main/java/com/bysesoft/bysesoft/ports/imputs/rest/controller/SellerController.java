package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Seller;
import com.bysesoft.bysesoft.domain.service.SellerService;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SellerDto;
import com.bysesoft.bysesoft.ports.imputs.rest.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController{

    private final SellerService sellerService;
    private final SellerMapper sellerMapper;

    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        sellerService.create(seller);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> findByName(@PathVariable @NotNull String name) {
        Seller seller = sellerService.getSellerByName(name);
        SellerDto sellerDto = sellerMapper.toDto(seller);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<Seller> sellers = new ArrayList<>(sellerService.getAll());
        List<SellerDto> sellerDtos = sellerMapper.toDtoList(sellers);
        return new ResponseEntity<>(sellerDtos,HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        sellerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
