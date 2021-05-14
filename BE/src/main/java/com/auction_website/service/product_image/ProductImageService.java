package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;

public interface ProductImageService {

    Iterable<ProductImage> getAllImageByProductId(int productId);
}
