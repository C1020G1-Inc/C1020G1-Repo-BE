package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    /**
     * Author : TungNT
     * Get all product
     */
    @Query(value = "select product from Product product " +
            "where product.productStatus.id not in(5 , 6)")
    List<Product> getAllProduct();


    /**
     * Author : TungNT
     * Search product multi cases
     */
    @Query("select product from Product product " +
            "join Account account on product.account.accountId = account.accountId " +
            "join User user on account.user.userId = user.userId " +
            "where (:productName is null or product.productName like %:productName%) and " +
            "(:categoryId is null or product.category.id = :categoryId) and " +
            "(:userName is null or user.userName like %:userName%) and " +
            "(:productStatusId is null or product.productStatus.id = :productStatusId)")
    List<Product> getProductBySearch(String productName, Integer categoryId,
                                     String userName, Integer productStatusId);

    /**
     * Author : TungNT
     * Approved product to auction;
     */
    @Transactional
    @Modifying
    @Query("update Product product set product.productStatus.id = 2 where product.productId = :idProduct")
    void approvedProduct(Integer idProduct);

    /**
     * Author : TungNT
     * Statistics product by month or by year
     */
    @Query("select product from Product product" +
            " where ((:monthSearch is null) or ((function('month', product.endTime)) = :monthSearch)) and" +
            " (function('year', product.endTime) = :yearSearch)")
    List<Product> getProductByDate(Integer monthSearch, Integer yearSearch);

    /**
     * Author : TungNT
     * Get product by product's id.
     */
    @Query("select product from Product product " +
            "where product.productId = :productId")
    Product getProductById(Integer productId);



    @Query(value = "select * from product" +
            " where product.account_id = :id", nativeQuery = true)
    Page<Product> findAllProductRegisterByUserId(Pageable pageable, Integer id);

    @Modifying
    @Query(value = "update product set product_status_id = 6 where product_id = :id",nativeQuery = true)
    void updateStatus(Integer id);

    @Modifying
    @Query(value = "update product set product_register_time = now(), product_status_id = 1 , product_price = :price, product_description = :description where product_id = :id",nativeQuery = true)
    void updateProduct(Double price, String description, Integer id);

//    @Query(value = "select * from product where product.product_name = like :%name%  or product.product_price = like :price% ",nativeQuery = true)
//    Page<Product> findProductRegister(String name,String time, Double price,Pageable pageable);
}

