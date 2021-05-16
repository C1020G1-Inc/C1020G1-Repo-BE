package com.auction_website.repository;

import com.auction_website.dto.ProductAuctionResultDTO;
import com.auction_website.model.ProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Integer> {
    @Query(value = "select * from  product_transaction t where t.status = 'success'", nativeQuery = true)
    List<ProductTransaction> showAllProductResult();

    @Query(value = "select * from auction_website.product_transaction t join auction_website.product " +
            "p on t.product_id = p.product_id where t.status = 'success' and p.category_id = ?1 ", nativeQuery = true)
    List<ProductTransaction> showAllProductResultByCategory(Integer categoryId);

    @Query(value = "select * from auction_website.product_transaction t join auction_website.product " +
            "p on t.product_id = p.product_id where t.status = 'success' and p.product_last_price > 10000000", nativeQuery = true)
    List<ProductTransaction> showAllProductResultByHot();
}
