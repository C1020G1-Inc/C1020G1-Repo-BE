package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionService {
    Page<Auction> findAllProductAuction(Pageable pageable, Integer id);

}
