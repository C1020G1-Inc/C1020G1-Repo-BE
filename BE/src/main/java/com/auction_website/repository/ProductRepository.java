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

    /**
     * author: PhucPT
     * method: get product from DB by productId
     * @param productId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM `product` WHERE product_id = :productId")
    Product getProductById(@Param("productId") int productId);

    /**
     * author: PhucPT
     * method: find all product currently in auction
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM `product` WHERE product_status_id = 2")
    Iterable<Product> findAllProductApproved();

    /**
     * author: PhucPT
     * method: set last price has been bidding to product
     * @param productId
     * @param lastPrice
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE `product` SET product_last_price = :lastPrice WHERE product_id = :productId")
    int setLastPrice(@Param("productId") int productId, @Param("lastPrice") double lastPrice);

    /**
     * author: PhucPT
     * method: set product status by product Id
     * @param productId
     * @param statusId
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE `product` SET product_status_id = :statusId WHERE product_id = :productId")
    int setProductStatus(@Param("productId") int productId, @Param("statusId") int statusId);

    /**
     * author: PhucPT
     * method: set status product to fail and reset last price
     * @param productId
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE `product` SET product_status_id = 4, product_last_price = NULL WHERE product_id  = :productId")
    int setFailProduct(@Param("productId") int productId);
}
