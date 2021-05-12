package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product" +
            " where product.account_id = :id", nativeQuery = true)
    Page<Product> findAllProductRegisterByUserId(Pageable pageable, Integer id);


}

