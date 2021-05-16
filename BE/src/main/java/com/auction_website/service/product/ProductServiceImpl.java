package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
<<<<<<< HEAD
     * author: PhucPT
     * method: get current step price of a product
     * @param product
     * @return
     */
    @Override
    public double findCurrentStepPrice(Product product) {
        int currentStep;
        double price = (product.getLastPrice() == null) ? product.getPrice() : product.getLastPrice();
        if (price < 20000)
            currentStep = 1000;
        else if (price < 100000)
            currentStep = 5000;
        else if (price < 500000)
            currentStep = 10000;
        else if (price < 2000000)
            currentStep = 20000;
        else if (price < 5000000)
            currentStep = 50000;
        else if (price < 10000000)
            currentStep = 100000;
        else if (price < 20000000)
            currentStep = 200000;
        else if (price < 50000000)
            currentStep = 500000;
        else if (price < 100000000)
            currentStep = 1000000;
        else
            currentStep = 2000000;
        return (currentStep > product.getPriceStep()) ? currentStep : product.getPriceStep();
    }

    /**
     * author: PhucPT
     * method: get product by id
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(int productId) {
        return productRepository.getProductById(productId);
    }

    /**
     * author: PhucPT
     * method: set last price for a product
     * @param productId
     * @param lastPrice
     * @return
     */
    @Override
    @Transactional
    public boolean setLastPrice(int productId, double lastPrice) {
        return productRepository.setLastPrice(productId, lastPrice) > 0;

    }

    /**
     * author: PhucPT
     * method: set product status for a product
     * @param productId
     * @param statusId
     * @return
     */
    @Override
    @Transactional
    public boolean setProductStatus(int productId, int statusId) {
        return productRepository.setProductStatus(productId, statusId) > 0;
    }

    /**
     * author: PhucPT
     * method: set last price to null and product status to fail for a product
     * @param productId
     * @return
     */
    @Override
    @Transactional
    public boolean resetProduct(int productId) {
        return productRepository.setFailProduct(productId) > 0;
    }

    /**
     * author: PhucPT
     * method: get all product has been approved
     * @return
     */
    @Override
    public Iterable<Product> findAllProductApproved() {
        return productRepository.findAllProductApproved();
    }

    /**
     * CuongNVM
     * list product register
     */
    @Override
    public Page<Product> findAllProductRegister(Pageable pageable, Integer id) {
        return productRepository.findAllProductRegisterByUserId(pageable, id);
    }

    /**
     * CuongNVM
     * Update status for button Cancel items register
     */
    @Override
    public void updateStatus(Integer id) {
        this.productRepository.updateStatus(id);
    }

    /**
     * CuongNVM
     * Update status for button "Đăng kí lại" items
     */
    @Override
    public void updateProduct(Double price, String description, Integer id) {

        this.productRepository.updateProduct(price, description, id);
    }

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
        long time = new Date().getTime();
        Timestamp registerTime = new Timestamp(time);
        productRepository.approvedProduct(idProduct,registerTime);
    }


    /**
     * Author: Unknown
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(Integer productId) {
        return productRepository.getProductById(productId);
    }

}
