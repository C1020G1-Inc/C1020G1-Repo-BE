package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Transactional
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
            value = "UPDATE `product` SET product_status_id = 5, product_last_price = NULL WHERE product_id  = :productId")
    int setFailProduct(@Param("productId") int productId);

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
    @Query("update Product product set product.productStatus.id = 2 , product.registerTime = :registerTime  where product.productId = :idProduct")
    void approvedProduct(Integer idProduct , Timestamp registerTime);

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

    /**
     * Author: CuongNVM
     * @param pageable
     * @param id
     * @return
     */
    @Query(value = "select * from product" +
            " where product.account_id = :id", nativeQuery = true)
    Page<Product> findAllProductRegisterByUserId(Pageable pageable, Integer id);

    /**
     * Author: CuongNVM
     * @param id
     */
    @Modifying
    @Query(value = "update product set product_status_id = 6 where product_id = :id",nativeQuery = true)
    void updateStatus(Integer id);

    /**
     * Author: CuongNVM
     * @param price
     * @param description
     * @param id
     */
    @Modifying
    @Query(value = "update product set product_register_time = now(), product_status_id = 1 , product_price = :price, product_description = :description where product_id = :id",nativeQuery = true)
    void updateProduct(Double price, String description, Integer id);

    /**
     * Author : TungNT
     * Statistics product by month , by year or by day
     */
    @Query("select product from Product product" +
            " where ((:monthSearch is null) or ((function('month', product.endTime)) = :monthSearch)) and" +
            " ((:daySearch is null) or ((function('day', product.endTime)) = :daySearch)) and" +
            " (function('year', product.endTime) = :yearSearch)")
    List<Product> getProductByDateForDonutChart(Integer daySearch,Integer monthSearch, Integer yearSearch);

}

