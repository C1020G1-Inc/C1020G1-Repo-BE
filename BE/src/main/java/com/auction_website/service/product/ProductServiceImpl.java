package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAllProductRegister(Pageable pageable, Integer id) {
        return productRepository.findAllProductRegisterByUserId(pageable,id);
    }
}
