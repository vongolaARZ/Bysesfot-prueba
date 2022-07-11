package com.bysesoft.bysesoft.ports.imputs.rest.controller;

import com.bysesoft.bysesoft.domain.model.Sales;
import com.bysesoft.bysesoft.domain.service.SalesService;
import com.bysesoft.bysesoft.ports.imputs.rest.dtos.SalesDto;
import com.bysesoft.bysesoft.ports.imputs.rest.mapper.SalesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
 @RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    private final SalesMapper salesMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sales sales){

        salesService.create(sales);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Sales> salesList= new ArrayList<>(salesService.findAll());
        List<SalesDto> salesDtoList = salesMapper.salesToDtoList(salesList);
        return new ResponseEntity<>(salesDtoList,HttpStatus.OK);
    }
}
