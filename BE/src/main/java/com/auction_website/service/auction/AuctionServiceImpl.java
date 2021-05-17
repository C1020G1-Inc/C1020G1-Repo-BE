package com.auction_website.service.auction;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Service
public class AuctionServiceImpl implements AuctionService {
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

     /** author: PhucPT
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

    /**
     * Author: CuongNVM
     * List product auction
     */
    @Override
    public Page<Auction> findAllProductAuction(Pageable pageable, Integer id) {
        return auctionRepository.findAllProductAuctionByUserId(pageable, id);
    }

    /**
     * Author: Unknown
     * @param productId
     * @return
     */
    @Override
    public List<Auction> getAllAuctionByProductId(Integer productId) {
        return auctionRepository.getAllAuctionByProductId(productId);
    }
}
