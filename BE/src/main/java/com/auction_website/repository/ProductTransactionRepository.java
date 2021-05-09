package com.auction_website.repository;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Integer> {
}
