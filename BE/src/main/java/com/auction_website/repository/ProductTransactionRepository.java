package com.auction_website.repository;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Integer> {
    /**
     * author: PhucPT
     * method: create new product transaction
     *
     * @param productId
     * @param accountId
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO `product_transaction` (product_id, account_id, status, auction_id,transaction_time) VALUES (:productId, :accountId, 'purchasing',:auctionId,:transactionTime)", countQuery = "SELECT LAST_INSERT_ID()")
    int createProductTransaction(int productId, int accountId, int auctionId, Timestamp transactionTime);

    /**
     * author: PhucPT
     * method: find all product transaction in purchasing
     *
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM `product_transaction` WHERE status = 'purchasing'")
    Iterable<ProductTransaction> findAllPurchasingTransaction();

    /**
     * author: PhucPT
     * method: find transaction in purchasing by product id
     *
     * @param productId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM product_transaction WHERE product_id = :productId AND status = 'purchasing'")
    ProductTransaction findCurrentTransactionByProductId(int productId);

    /**
     * author: PhucPT
     * method: get transaction by id
     *
     * @param productTransactionId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM product_transaction WHERE product_transaction_id = :productTransactionId")
    ProductTransaction getTransactionById(int productTransactionId);

    /**
     * author: PhucPT
     * method: set status of a transaction id
     *
     * @param status
     * @param productTransactionId
     */
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE product_transaction SET status = :status WHERE product_transaction_id = :productTransactionId")
    void setStatusByTransactionId(String status, int productTransactionId);

    /**
     * author: PhucPT
     * method: 
     * @param accountId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM product_transaction WHERE status = 'purchasing' AND account_id = :accountId")
    Iterable<ProductTransaction> getCurrentTransactionByAccountId(int accountId);
}
