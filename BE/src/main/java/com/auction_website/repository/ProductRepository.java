package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Author : CaoLPT
     * function to change status of product
     * @param productStatusId
     */
    @Modifying
    @Query(value = "UPDATE product SET product_status_id = :productStatusId WHERE product_id = :productId", nativeQuery = true)
    void changeProductStatus(@Param("productStatusId") Integer productStatusId, @Param("productId") Integer productId);
}
