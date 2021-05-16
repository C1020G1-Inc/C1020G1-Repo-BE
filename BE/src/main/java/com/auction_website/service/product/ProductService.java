package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.model.dto.ProductDTO;

public interface ProductService {
    /**
     * author: ThinhTHB
     * method: create product
     * */
    void postProduct(Product product);
}
