package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import com.auction_website.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public List<Auction> getAllAuctionByProductId(Integer productId) {
        return auctionRepository.getAllAuctionByProductId(productId);
    }
}
