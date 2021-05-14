package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import com.auction_website.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private AuctionRepository auctionRepository;

    /**
     * Author: CuongNVM
     * List product auction
     */
    @Override
    public Page<Auction> findAllProductAuction(Pageable pageable, Integer id) {
        return auctionRepository.findAllProductAuctionByUserId(pageable, id);
    }
}
