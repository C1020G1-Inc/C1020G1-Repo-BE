package com.auction_website.service.auction;

import com.auction_website.model.Auction;

import java.util.List;

public interface AuctionService {
    List<Auction> showTopProductAuction(Integer category);
}
