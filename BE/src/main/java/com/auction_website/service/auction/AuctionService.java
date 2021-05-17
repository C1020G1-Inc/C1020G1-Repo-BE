package com.auction_website.service.auction;

import com.auction_website.model.Auction;

import java.util.List;


import com.auction_website.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface AuctionService {
    List<Auction> showTopProductAuction(Integer category);

    boolean createAuction(double price, Date timeAuction, int accountId, int productId);

    Iterable<Auction> getListAuctionInProgressByProductId(Product product);

    boolean setStatusAuctionInProgressByProductId(int productId, String status);

    boolean setStatusAuctionByAuctionId(int auctionId, String status);

    Auction getHighestAuctionInProgressByProductId(int productId);

    Page<Auction> findAllProductAuction(Pageable pageable, Integer id);

    List<Auction> getAllAuctionByProductId(Integer productId);
}
