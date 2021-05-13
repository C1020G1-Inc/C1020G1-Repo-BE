package com.auction_website.repository;

import com.auction_website.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    @Query("select productImage from ProductImage productImage " +
            "where productImage.product.productId = :productId")
    List<ProductImage> getAllProductImageByProductId(Integer productId);


    @Transactional
    @Modifying
    @Query("delete from ProductImage productImage " +
            "where productImage.product.productId = :productId")
    void deleteByProductId(Integer productId);
}
