package com.auction_website.repository;

import com.auction_website.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    @Query(value = "SELECT * FROM auction Where product_id = ?1" , nativeQuery = true)
    List<Auction> getAllAuctionByProductId(Integer productId) ;

}
