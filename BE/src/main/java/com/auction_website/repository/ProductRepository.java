package com.auction_website.repository;

import com.auction_website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product", nativeQuery = true)
    List<Product> getAllProduct();

    @Query("select product from Product product " +
            "join Account account on product.account.accountId = account.accountId " +
            "join User user on account.user.userId = user.userId " +
            "where (:productName is null or product.productName like %:productName%) and " +
            "(:categoryId is null or product.category.id = :categoryId) and " +
            "(:userName is null or user.userName like %:userName%) and " +
            "(:productStatusId is null or product.productStatus.id = :productStatusId)")
    List<Product> getProductBySearch(String productName, Integer categoryId,
                                     String userName, Integer productStatusId);

    @Transactional
    @Modifying
    @Query("update Product product set product.productStatus.id = 2 where product.productId = :idProduct")
    void approvedProduct(Integer idProduct);

    @Query("select product from Product product" +
            " where ((:monthSearch is null) or ((function('month', product.endTime)) = :monthSearch)) and" +
            " ((function('year', product.endTime)) = :yearSearch)")
    List<Product> getProductByDate(Integer monthSearch, Integer yearSearch);
}
