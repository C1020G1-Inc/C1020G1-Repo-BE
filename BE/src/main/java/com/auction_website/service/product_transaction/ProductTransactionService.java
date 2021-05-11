package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;

public interface ProductTransactionService {

    int createProductTransaction(int productId, int accountId);

    Iterable<ProductTransaction> findAllPurchasingTransaction();

    ProductTransaction findCurrentTransactionByProductId(int productId);

    ProductTransaction getTransactionById(int productTransactionId);

    void setStatusByTransactionId(String status, int productTransactionId);
}
