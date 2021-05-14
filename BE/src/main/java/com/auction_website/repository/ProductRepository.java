package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select p from Product p where p.productId = ?1")
    Product getProductByProductId( int productId);
}
