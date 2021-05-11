package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM product \n" +
            "WHERE product_id = ?1", nativeQuery = true)
    Product getProductById(Integer productId);
}
