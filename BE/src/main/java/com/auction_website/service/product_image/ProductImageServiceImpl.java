package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;
import com.auction_website.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    private ProductImageRepository productImageRepository;

    /**
     * author: ThinhTHB
     * method: post image
     * */
    @Override
    public void postImage(ProductImage productImage) {
        productImageRepository.includeImage(productImage);
    }
}
