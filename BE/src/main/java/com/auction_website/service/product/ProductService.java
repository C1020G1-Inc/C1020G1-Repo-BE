package com.auction_website.service.product;

import com.auction_website.model.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    double findCurrentStepPrice(Product product);

    Product getProductById(int productId);

    boolean setLastPrice(@Param("productId") int productId, @Param("lastPrice") double lastPrice);

    boolean setProductStatus(@Param("productId") int productId, @Param("statusId") int statusId);

    @Transactional
    boolean resetProduct(@Param("productId") int productId);

    Iterable<Product> findAllProductApproved();

}
