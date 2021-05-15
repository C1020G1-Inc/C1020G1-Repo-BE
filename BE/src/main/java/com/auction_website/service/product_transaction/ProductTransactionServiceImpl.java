package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;
import com.auction_website.repository.ProductTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
public class ProductTransactionServiceImpl implements ProductTransactionService {
    @Autowired
    private ProductTransactionRepository productTransactionRepository;

    /**
     * author: PhucPT
     * method: create new product transaction with given productId and accountID
     * @param productId
     * @param accountId
     * @return
     */
    @Override
    @Transactional
    public int createProductTransaction(int productId, int accountId, int auctionId, Timestamp transactionTime) {
        return productTransactionRepository.createProductTransaction(productId, accountId,auctionId,transactionTime);
    }

    /**
     * author: PhucPT
     * method: get all transaction in progress
     * @return
     */
    @Override
    public Iterable<ProductTransaction> findAllPurchasingTransaction() {
        return productTransactionRepository.findAllPurchasingTransaction();
    }

    /**
     * author: PhucPT
     * method: get transaction in purchasing by product id
     * @param productId
     * @return
     */
    @Override
    public ProductTransaction findCurrentTransactionByProductId(int productId) {
        return productTransactionRepository.findCurrentTransactionByProductId(productId);
    }

    /**
     * author: PhucPT
     * method: get transaction by id
     * @param productTransactionId
     * @return
     */
    @Override
    public ProductTransaction getTransactionById(int productTransactionId) {
        return productTransactionRepository.getTransactionById(productTransactionId);
    }

    /**
     * author: PhucPT
     * method: set status of a transaction
     * @param status
     * @param productTransactionId
     */
    @Override
    @Transactional
    public void setStatusByTransactionId(String status, int productTransactionId) {
        productTransactionRepository.setStatusByTransactionId(status,productTransactionId);
    }

    /**
     * author: PhucPT
     * method: get current transactions by account id
     * @param accountId
     * @return
     */
    @Override
    public Iterable<ProductTransaction> getCurrentTransactionByAccountId(int accountId) {
        return productTransactionRepository.getCurrentTransactionByAccountId(accountId);
    }
}
