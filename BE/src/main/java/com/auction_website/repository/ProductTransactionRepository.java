package com.auction_website.repository;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

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
     *
     * @param accountId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM product_transaction WHERE status = 'purchasing' AND account_id = :accountId")
    Iterable<ProductTransaction> getCurrentTransactionByAccountId(int accountId);

    /**
     * Author: DungNV
     *
     * @return
     */
    @Query(value = "SELECT *" +
            " FROM product_transaction" +
            " WHERE status = 'Thành công' or status = 'Thất bại'", nativeQuery = true)
    List<ProductTransaction> getAllTransaction();

    /**
     * Author: DungNV
     *
     * @param transactionId
     * @return
     */
    @Query(value = "SELECT *" +
            " FROM product_transaction" +
            " WHERE product_transaction_id = ?1", nativeQuery = true)
    ProductTransaction findTransaction(Integer transactionId);

    /**
     * Author: DungNV
     *
     * @param transactionId
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_transaction" +
            " WHERE product_transaction_id = ?1", nativeQuery = true)
    void deleteTransaction(Integer transactionId);

    /**
     * Author: DungNV
     *
     * @param namePost
     * @param nameBuy
     * @param productName
     * @param price
     * @param status
     * @return
     */
    @Query(value = "SELECT pt" +
            " FROM ProductTransaction pt" +
            " JOIN Auction a on pt.auction.auctionId = a.auctionId" +
            " JOIN Product p on a.product.productId = p.productId" +
            " JOIN Account ac on p.account.accountId = ac.accountId" +
            " JOIN User u on ac.user.userId = u.userId" +
            " WHERE (:namePost is null or pt.product.account.user.userName like %:namePost%) and" +
            " (:nameBuy is null or pt.account.user.userName like %:nameBuy%) and" +
            " (:productName is null or pt.product.productName like %:productName%) and" +
            " (:price is null or pt.auction.price = :price) and" +
            " (:status is null or pt.status = :status) and" +
            " pt.status <> 'Đang chờ'")
    List<ProductTransaction> getTransactionBySearch(String namePost, String nameBuy, String productName, Double price, String status);

}
