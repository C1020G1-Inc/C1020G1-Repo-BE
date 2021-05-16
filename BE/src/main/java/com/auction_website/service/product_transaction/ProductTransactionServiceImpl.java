package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;
import com.auction_website.repository.ProductTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTransactionServiceImpl implements ProductTransactionService {
    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    /**
     * Method: Get result product auction.
     * Author: HanTH
     * @param category
     * @return
     */
    @Override
    public List<ProductTransaction> showAllProductResult(Integer category) {
        switch (category) {
            case 0:
                return productTransactionRepository.showAllProductResult();
            case 4:
                return productTransactionRepository.showAllProductResultByHot();
            default:
                return productTransactionRepository.showAllProductResultByCategory( category );
        }
    }
}
