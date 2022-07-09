package com.bysesoft.bysesoft.domain.service.implementation;

import com.bysesoft.bysesoft.domain.model.Seller;
import com.bysesoft.bysesoft.domain.repository.SellerRepository;
import com.bysesoft.bysesoft.domain.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;


    @Override
    public void create(Seller seller) {
        sellerRepository.save(seller);
    }

    @Override
    public Seller getSellerByName(String name) {
        return sellerRepository.findByName(name)
                .orElseThrow(()->new RuntimeException("no se encontro"));
    }

    @Override
    public List<Seller> getAll() {
        List<Seller> sellers = sellerRepository.findAll();

        if(sellers.isEmpty()){
            throw new RuntimeException("no hay vendedores");
        }else {
            return  sellers;
        }
    }

    @Override
    public void deleteById(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("no se encontro"));

        sellerRepository.deleteById(id);
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro"));
    }
}
