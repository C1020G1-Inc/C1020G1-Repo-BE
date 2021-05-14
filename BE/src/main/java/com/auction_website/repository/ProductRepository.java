package com.auction_website.repository;

import com.auction_website.dto.ProductAuctionResultDTO;
import com.auction_website.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select new com.auction_website.dto.ProductAuctionResultDTO(p.product_id as product_id, p.product_name as product_name,p.product_last_price as last_price, u.user_name as user_name) from  auction_website.product_image img\n" +
            "join auction_website.product p on p.product_id = img.product_id\n" +
            "join auction_website.product_transaction t on t.product_id = p.product_id\n" +
            "join auction_website.`account` a on a.account_id = t.account_id\n" +
            "join auction_website.`user` u on u.user_id = a.user_id\n" +
            "where t.`status` = 'success'\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductAuctionResultDTO> showAllProductResult();

    @Query(value = "select p.product_id as product_id, p.product_name as product_name,p.product_last_price as last_price, u.user_name as user_name from auction_website.product_image img\n" +
            "join auction_website.product p on p.product_id = img.product_id\n" +
            "join auction_website.product_transaction t on t.product_id = p.product_id\n" +
            "join auction_website.`account` a on a.account_id = t.account_id\n" +
            "join auction_website.`user` u on u.user_id = a.user_id\n" +
            "where t.`status` = 'success' and p.category_id = ?1 \n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductAuctionResultDTO> showAllProductResultByCategory(Integer categoryId);

    @Query(value = "select p.product_id as product_id, p.product_name as product_name,p.product_last_price as last_price, u.user_name as user_name from auction_website.product_image img\n" +
            "join auction_website.product p on p.product_id = img.product_id\n" +
            "join auction_website.product_transaction t on t.product_id = p.product_id\n" +
            "join auction_website.`account` a on a.account_id = t.account_id\n" +
            "join auction_website.`user` u on u.user_id = a.user_id\n" +
            "where t.`status` = 'success' and (p.product_last_price > 10000000)\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductAuctionResultDTO> showAllProductResultByHot();

}
