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

    /**
     * Method: get top product auction.
     * Author: HanTH
     * @param category
     * @return
     */
    @Override
    public List<Auction> showTopProductAuction(Integer category) {
        switch (category){
            case 0:
                return auctionRepository.showTopProductAuctionAll();
            case 4:
                return auctionRepository.showTopProductAuctionByHot();
            default:
                return auctionRepository.showTopProductAuctionByCategory( category );
        }
    }
}
