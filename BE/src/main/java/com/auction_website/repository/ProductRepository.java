package com.auction_website.repository;

import com.auction_website.model.Product;
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
            "product_price_step, product_description, product_status_id, account_id, product_register_time) " +
            "values (:#{#product.productName}, :#{#product.category}, :#{#product.quantity}, :#{#product.price}, :#{#product.priceStep}" +
            ", :#{#product.description}, :#{#product.productStatus}, :#{#product.account}, :#{#product.registerTime})",nativeQuery = true)
    void postProduct(Product product);

}
