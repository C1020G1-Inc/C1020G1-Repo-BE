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

    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public List<Product> getProductBySearch(String productName, Integer categoryId, String userName, Integer productStatusId) {
        return productRepository.getProductBySearch(productName, categoryId, userName, productStatusId);
    }

    @Override
    public List<Product> getProductByDate(Integer monthSearch, Integer yearSearch) {
        return productRepository.getProductByDate(monthSearch, yearSearch);
    }

    @Override
    public void editProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void approvedProduct(Integer idProduct) {
        productRepository.approvedProduct(idProduct);
    }
}
