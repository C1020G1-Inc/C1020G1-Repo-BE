package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    /**
     * author: ThinhTHB
     * method: create product
     * */
    public void postProduct(Product product){
        productRepository.postProduct(product);
    }

}
