package com.auction_website.repository;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Integer> {

    @Query(value = "SELECT *" +
            " FROM product_transaction" +
            " WHERE status = 'Thành công' or status = 'Thất bại'", nativeQuery = true)
    List<ProductTransaction> getAllTransaction();

    @Query(value = "SELECT *" +
            " FROM product_transaction" +
            " WHERE product_transaction_id = ?1", nativeQuery = true)
    ProductTransaction findTransaction(Integer transactionId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_transaction" +
            " WHERE product_transaction_id = ?1", nativeQuery = true)
    void deleteTransaction(Integer transactionId);

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
