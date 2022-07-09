package com.bysesoft.bysesoft.domain.service.implementation;

import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.model.Sales;
import com.bysesoft.bysesoft.domain.repository.SalesRepository;
import com.bysesoft.bysesoft.domain.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    @Transactional
    @Override
    public void create(Sales sales) {
        Sales salesEntity = new Sales();
        salesEntity.setTotal(sales.getProductList()
                .stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .orElse(0.0)
        );
        salesEntity.setSeller(sales.getSeller());
        salesEntity.setProductList(sales.getProductList());

        if(salesEntity.getProductList().size() <= 2){
            salesEntity.setCommission(5 * salesEntity.getTotal() / 100);
        }else{
            salesEntity.setCommission(10*salesEntity.getTotal()/100);
        }

        salesRepository.save(salesEntity);


    }

    @Transactional(readOnly = true)
    @Override
    public Sales findById(Long id) {
        return salesRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("no se encontro la venta"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Sales> findAll() {
       List<Sales> sales = salesRepository.findAll();

       if(sales.isEmpty()){
           throw new RuntimeException("No hay ninguna venta");
       }else{
           return sales;
       }
    }
}
