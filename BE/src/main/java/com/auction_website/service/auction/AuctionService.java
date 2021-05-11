package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;

import java.util.Date;

public interface AuctionService {
    boolean createAuction(double price, Date timeAuction, int accountId, int productId);

    Iterable<Auction> getListAuctionInProgressByProductId(int productId);

    boolean setStatusAuctionInProgressByProductId(int productId, String status);

    boolean setStatusAuctionByAuctionId(int auctionId, String status);

    Auction getHighestAuctionInProgressByProductId(int productId);
}
