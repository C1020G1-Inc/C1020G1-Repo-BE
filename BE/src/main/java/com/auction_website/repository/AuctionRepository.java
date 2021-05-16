package com.auction_website.repository;

import com.auction_website.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    @Query(value = "SELECT * FROM auction_website.auction a\n" +
            "join product p on p.product_id = a.product_id\n" +
            "group by p.product_id\n" +
            "order by count(p.product_id) desc\n" +
            "limit 10", nativeQuery = true)
    List<Auction> showTopProductAuctionAll();

    @Query(value = "SELECT * FROM auction_website.auction a\n" +
            "join product p on p.product_id = a.product_id\n" +
            "where p.category_id = ?1\n" +
            "group by p.product_id\n" +
            "order by count(p.product_id) desc\n" +
            "limit 10", nativeQuery = true)
    List<Auction> showTopProductAuctionByCategory(Integer category);

    @Query(value = "SELECT * FROM auction_website.auction a\n" +
            "join product p on p.product_id = a.product_id\n" +
            "where p.category_id = 1\n" +
            "group by p.product_id\n" +
            "order by count(p.product_id) desc\n" +
            "limit 10", nativeQuery = true)
    List<Auction> showTopProductAuctionByHot();
}
