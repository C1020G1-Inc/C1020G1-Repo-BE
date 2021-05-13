package com.auction_website.service.product;

import com.auction_website.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAllProductRegister(Pageable pageable, Integer id);
    void updateStatus(Integer id);
    void updateProduct(Double price, String description, Integer id);
}
