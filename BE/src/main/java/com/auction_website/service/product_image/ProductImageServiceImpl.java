package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;
import com.auction_website.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> getImagesProductById(Integer productId) {
        return productImageRepository.getAllProductImageByProductId(productId);
    }

    @Override
    public void deleteImagesById(Integer idProduct) {
        productImageRepository.deleteByProductId(idProduct);
    }

    @Override
    public void saveProductImage(ProductImage productImage) {
        productImageRepository.save(productImage);
    }


}
