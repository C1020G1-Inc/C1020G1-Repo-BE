package com.auction_website.service.auction;

import com.auction_website.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private AuctionRepository auctionRepository;
}
