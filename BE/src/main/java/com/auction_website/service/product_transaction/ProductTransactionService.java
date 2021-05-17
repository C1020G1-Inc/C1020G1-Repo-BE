package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;

import java.util.List;
import java.sql.Timestamp;

public interface ProductTransactionService {

    List<ProductTransaction> showAllProductResult(Integer category);

    int createProductTransaction(int productId, int accountId, int auctionId, Timestamp transactionTime);

    Iterable<ProductTransaction> findAllPurchasingTransaction();

    ProductTransaction findCurrentTransactionByProductId(int productId);

    ProductTransaction getTransactionById(int productTransactionId);

    void setStatusByTransactionId(String status, int productTransactionId);

    Iterable<ProductTransaction> getCurrentTransactionByAccountId(int accountId);

    /**
     * Author: DungNv
     * @return
     */
    List<ProductTransaction> getAllTransaction();

    /**
     * Author: DungNV
     * @param namePost
     * @param nameBuy
     * @param productName
     * @param price
     * @param status
     * @return
     */
    List<ProductTransaction> getTransactionBySearch(String namePost, String nameBuy, String productName, Double price, String status);

    /**
     * Author: DungNV
     * @param transactionId
     * @return
     */
    ProductTransaction findTransaction(Integer transactionId);

    /**
     * Author: DungNV
     * @param transactionId
     */
    void deleteTransaction(Integer transactionId);

}
