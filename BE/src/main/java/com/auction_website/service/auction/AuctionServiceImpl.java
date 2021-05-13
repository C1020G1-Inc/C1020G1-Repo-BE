package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    /**
     * author: PhucPT
     * method: save new auction with given price, timeAuction, accountId and productId
     * @param price
     * @param timeAuction
     * @param accountId
     * @param productId
     * @return
     */
    @Override
    @Transactional
    public boolean createAuction(double price, Date timeAuction, int accountId, int productId) {
        return auctionRepository.createAuction(price, timeAuction, accountId, productId) > 0;
    }


    /**
     * author: PhucPT
     * method: get all auction has been submit in current auction session by product
     * @param product
     * @return
     */
    @Override
    public Iterable<Auction> getListAuctionInProgressByProductId(Product product) {
        Timestamp endTime = new Timestamp(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000);
        return auctionRepository.getListAuctionInProgressByProductId(product.getProductId(),product.getRegisterTime(),endTime);
    }

    /**
     * author: PhucPT
     * method: set status all auction in progress by productId
     * @param productId
     * @param status
     * @return
     */
    @Override
    @Transactional
    public boolean setStatusAuctionInProgressByProductId(int productId, String status) {
        return auctionRepository.setStatusAuctionInProgressByProductId(productId, status) > 0;
    }

    /**
     * author: PhucPT
     * method: set status of specific auction
     * @param auctionId
     * @param status
     * @return
     */
    @Override
    @Transactional
    public boolean setStatusAuctionByAuctionId(int auctionId, String status) {
        return auctionRepository.setStatusAuctionByAuctionId(auctionId, status) > 0;
    }

    /**
     * author: PhucPT
     * method: get highest auction of a product
     * @param productId
     * @return
     */
    @Override
    public Auction getHighestAuctionInProgressByProductId(int productId) {
        return auctionRepository.getHighestAuctionInProgressByProductId(productId);
    }

}
