package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.dto.ProductDTO;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    /**
     * author: ThinhTHB
     * method: create product
     * */
    @Override
    @Transactional
    public void postProduct(Product product) {
//        ProductImage image = productDTO.getDTOImage();
        productRepository.createProduct(product);
    }

}
