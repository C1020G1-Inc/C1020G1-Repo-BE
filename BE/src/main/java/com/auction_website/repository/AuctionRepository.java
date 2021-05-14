package com.auction_website.repository;

import com.auction_website.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    @Query(value = "select * from auction where auction.account_id = :id", nativeQuery = true)
    Page<Auction> findAllProductAuctionByUserId(Pageable pageable, Integer id);
}
