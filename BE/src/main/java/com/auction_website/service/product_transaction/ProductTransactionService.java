package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;

import java.sql.Timestamp;

public interface ProductTransactionService {

    int createProductTransaction(int productId, int accountId, int auctionId, Timestamp transactionTime);

    Iterable<ProductTransaction> findAllPurchasingTransaction();

    ProductTransaction findCurrentTransactionByProductId(int productId);

    ProductTransaction getTransactionById(int productTransactionId);

    void setStatusByTransactionId(String status, int productTransactionId);

    Iterable<ProductTransaction> getCurrentTransactionByAccountId(int accountId);

}
