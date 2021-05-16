package com.auction_website.repository;

import com.auction_website.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    @Transactional

    /**
     * author: ThinhTHB
     * method: post image
     * */
    @Modifying
    @Query(value = "insert into product_image (image, product_id)" +
            " value (:#{#productImage.image} ,:#{#productImage.product})" ,nativeQuery = true)
    void includeImage(ProductImage productImage);

}

