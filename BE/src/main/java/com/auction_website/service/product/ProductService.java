package com.auction_website.service.product;

import com.auction_website.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    List<Product> getProductBySearch(String productName , Integer categoryId , String userName , Integer productStatusId);
    List<Product> getProductByDate(Integer monthSearch , Integer yearSearch);
    void editProduct(Product product);
    void approvedProduct(Integer idProduct);
    Product getProductById(Integer productId);
}
