package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;
import com.auction_website.repository.ProductTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTransactionServiceImpl implements ProductTransactionService {
    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    @Override
    public List<ProductTransaction> getAllTransaction() {
        return productTransactionRepository.getAllTransaction();
    }

    @Override
    public List<ProductTransaction> getTransactionBySearch(String namePost, String nameBuy, String productName, Double price, String status) {
        return productTransactionRepository.getTransactionBySearch(namePost, nameBuy, productName, price, status);
    }

    @Override
    public ProductTransaction findTransaction(Integer transactionId) {
        return productTransactionRepository.findTransaction(transactionId);
    }

    @Override
    public void deleteTransaction(Integer transactionId) {
        productTransactionRepository.deleteTransaction(transactionId);
    }
}
