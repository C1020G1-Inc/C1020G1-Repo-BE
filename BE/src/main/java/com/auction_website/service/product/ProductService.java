package com.auction_website.service.product;

import com.auction_website.model.Product;

public interface ProductService {
    /**
     * Author : SonPH
     * find product by productID
     *
     * @param productId
     */
    Product getProductById(Integer productId);


}
