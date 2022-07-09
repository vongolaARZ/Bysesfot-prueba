package com.bysesoft.bysesoft.domain.service.implementation;

import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.repository.ProductRespository;
import com.bysesoft.bysesoft.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRespository productRespository;

    @Transactional(readOnly = true)
    @Override
    public Product getByName(@PathVariable String name){
         return productRespository.findByName(name)
                 .orElseThrow(()->new RuntimeException("no se encontro ningun producto con este nombre"));
    }

    @Transactional
    @Override
    public void create ( Product product){productRespository.save(product);}

    @Override
    @Transactional(readOnly = true)
    public Product getByCatergory(String category) {
        return productRespository.findByCategory(category)
                .orElseThrow(()-> new RuntimeException("no se encontro ningun producto con esta categoria"));
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
       Product entity = productRespository.findById(id)
               .orElseThrow(()-> new RuntimeException("no se encontro el producto "));
       productRespository.deleteById(entity.getProductId());
    }

    @Override
    public List<Product> findAll() {

        List<Product> products = productRespository.findAll();

        if(products.isEmpty()){
            throw new RuntimeException("No hay productos");
        }else{
            return products;
        }
    }


}
