package com.auction_website.repository;

import com.auction_website.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    /**
     * author: PhucPT
     * method: get all product image by product id
     * @param productId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM product_image WHERE product_id = :productId")
    Iterable<ProductImage> getAllImageByProductId(int productId);
}
