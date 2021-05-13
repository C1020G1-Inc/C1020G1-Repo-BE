package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product" +
            " where product.account_id = :id", nativeQuery = true)
    Page<Product> findAllProductRegisterByUserId(Pageable pageable, Integer id);

    @Modifying
    @Query(value = "update product set product_status_id = 6 where product_id = :id",nativeQuery = true)
    void updateStatus(Integer id);

    @Modifying
    @Query(value = "update product set product_status_id = 1 , product_price = :price, product_description = :description where product_id = :id",nativeQuery = true)
    void updateProduct(Double price, String description, Integer id);
}

