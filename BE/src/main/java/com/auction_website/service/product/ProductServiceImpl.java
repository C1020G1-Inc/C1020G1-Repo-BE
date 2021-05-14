package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Author : TungNT
     * Get all product
     */
    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    /**
     * Author : TungNT
     * Search product multi cases
     */
    @Override
    public List<Product> getProductBySearch(String productName, Integer categoryId, String userName, Integer productStatusId) {
        return productRepository.getProductBySearch(productName, categoryId, userName, productStatusId);
    }

    /**
     * Author : TungNT
     * Statistics product by month or by year
     */
    @Override
    public List<Product> getProductByDate(Integer monthSearch, Integer yearSearch) {
        return productRepository.getProductByDate(monthSearch, yearSearch);
    }

    /**
     * Author : TungNT
     * Edit product
     */
    @Override
    public void editProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Author : TungNT
     * Approved product to auction;
     */
    @Override
    public void approvedProduct(Integer idProduct) {
        productRepository.approvedProduct(idProduct);
    }

    /**
     * Author : TungNT
     * Get product by product's id.
     */
    @Override
    public Product getProductById(Integer productId) {
        return productRepository.getProductById(productId);
    }
}
