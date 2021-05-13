package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> getImagesProductById(Integer productId);

    void deleteImagesById(Integer productId);

    void saveProductImage(ProductImage productImage);
}
