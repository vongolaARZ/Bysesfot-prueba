package com.bysesoft.bysesoft.domain.service.implementation;

import com.bysesoft.bysesoft.common.NotFoundException;
import com.bysesoft.bysesoft.domain.model.Category;
import com.bysesoft.bysesoft.domain.model.Product;
import com.bysesoft.bysesoft.domain.repository.CategoryRepository;
import com.bysesoft.bysesoft.domain.repository.ProductRespository;
import com.bysesoft.bysesoft.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRespository productRespository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public Product getByName(String name){
         return productRespository.findByName(name)
                 .orElseThrow(()->new NotFoundException("no se encontro ningun producto con este nombre"));
    }

    @Transactional
    @Override
    public void create ( Product product){
        Category category = categoryRepository.findByName(product.getCategory().getName())
                .orElseThrow(()->new NotFoundException("no se encontro la categoria ingresada"));

        Product productEntity = new Product();
        productEntity.setCategory(category);
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());

        productRespository.save(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCatergory(String category) {
        Category categoryEntity = categoryRepository.findByName(category)
                .orElseThrow(()->new NotFoundException("no existe la categoria ingresada"));

        return categoryEntity.getProducts();
    }
    @Override
    @Transactional
    public void deleteById(Long id) {
       Product entity = productRespository.findById(id)
               .orElseThrow(()-> new NotFoundException("no se encontro el producto "));
       productRespository.deleteById(entity.getProductId());
    }

    @Override
    public List<Product> findAll() {

        List<Product> products = productRespository.findAll();

        if(products.isEmpty()){
            throw new NotFoundException("No hay productos");
        }else{
            return products;
        }
    }


}
