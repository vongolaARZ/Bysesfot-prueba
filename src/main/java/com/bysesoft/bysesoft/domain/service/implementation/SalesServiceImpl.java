package com.bysesoft.bysesoft.domain.service.implementation;

import com.bysesoft.bysesoft.common.NotFoundException;
import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.model.Sales;
import com.bysesoft.bysesoft.domain.model.Seller;
import com.bysesoft.bysesoft.domain.repository.ProductRespository;
import com.bysesoft.bysesoft.domain.repository.SalesRepository;
import com.bysesoft.bysesoft.domain.repository.SellerRepository;
import com.bysesoft.bysesoft.domain.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final SellerRepository sellerRepository;
    private final ProductRespository productRespository;

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

        Seller seller = sellerRepository.findByName(sales.getSeller().getName())
                .orElseThrow(()-> new NotFoundException("no existe el vendedor ingresado"));

        salesEntity.setSeller(seller);

        List<Product> productList = sales.getProductList()
                .stream()
                .map(Product::getName)
                .map(name -> productRespository.findByName(name)
                        .orElseThrow(()-> new NotFoundException("no se encontro el producto")))
                .collect(Collectors.toList());


        salesEntity.setProductList(productList);

        if(salesEntity.getProductList().size() <= 2){
            salesEntity.setCommission(5 * salesEntity.getTotal() / 100);
        }else{
            salesEntity.setCommission(10*salesEntity.getTotal()/100);
        }

        seller.setSalary(seller.getSalary() + salesEntity.getCommission());

        sellerRepository.save(seller);

        salesRepository.save(salesEntity);



    }

    @Transactional(readOnly = true)
    @Override
    public Sales findById(Long id) {
        return salesRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("no se encontro la venta bajo este id"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Sales> findAll() {
       List<Sales> sales = salesRepository.findAll();

       if(sales.isEmpty()){
           throw new NotFoundException("No hay ninguna venta");
       }else{
           return sales;
       }
    }
}
