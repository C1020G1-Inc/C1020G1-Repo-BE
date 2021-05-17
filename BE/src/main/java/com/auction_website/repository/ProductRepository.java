package com.auction_website.repository;

import com.auction_website.model.Product;
//import com.auction_website.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional

    /**
     * author: ThinhTHB
     * method: create product
     * */
    @Modifying
    @Query(value = "insert into product (product_name, category_id, product_quantity, product_price, " +
            "product_price_step, product_description, product_status_id, product_register_time, product_auction_time) " +
            "value " +
            "(:#{#product.productName}, " +
            ":#{#product.category.id}, " +
            ":#{#product.quantity}, " +
            ":#{#product.price}, " +
            ":#{#product.priceStep}, " +
            ":#{#product.description}, " +
            ":#{#product.productStatus.id}, " +
            ":#{#product.registerTime}, " +
            ":#{#product.auctionTime})"
            , nativeQuery = true)
    void createProduct(Product product);

}
