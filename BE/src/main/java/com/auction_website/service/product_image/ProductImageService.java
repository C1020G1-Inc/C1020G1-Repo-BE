package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> showAllProductAuction(Integer category);

    List<ProductImage> showAllProductEndAuction(Integer category);

    ProductImage findOneByProductId(Integer productId);

    List<ProductImage> searchProductAuction(String keySearch, Integer category,Integer priceRange);
}
