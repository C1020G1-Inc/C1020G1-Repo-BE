package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuctionService {
    Page<Auction> findAllProductAuction(Pageable pageable, Integer id);
    List<Auction> getAllAuctionByProductId(Integer productId) ;
}
