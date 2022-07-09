package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Sales;
import com.bysesoft.bysesoft.domain.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sales sales){
       salesService.create(sales);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Sales> salesList= new ArrayList<>(salesService.findAll());
        return new ResponseEntity<>(salesList,HttpStatus.OK);
    }
}
