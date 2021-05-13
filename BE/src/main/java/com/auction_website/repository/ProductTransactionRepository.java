package com.auction_website.repository;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Integer> {

    @Query(value = "SELECT *" +
            " FROM product_transaction" +
            " WHERE status = 'Thành công' or status = 'Thất bại'", nativeQuery = true)
    Page<ProductTransaction> getAllTransaction(Pageable pageable);

    @Query(value = "SELECT *" +
            " FROM product_transaction" +
            " WHERE product_transaction_id = ?1", nativeQuery = true)
    ProductTransaction findTransaction(Integer transactionId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_transaction" +
            " WHERE product_transaction_id = ?1", nativeQuery = true)
    void deleteTransaction(Integer transactionId);
}
