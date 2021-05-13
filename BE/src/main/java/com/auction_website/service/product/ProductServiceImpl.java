package com.auction_website.service.product;

import com.auction_website.model.Product;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    /**
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


}
