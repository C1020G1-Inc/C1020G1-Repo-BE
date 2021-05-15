package com.auction_website.repository;

import com.auction_website.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer> {
    /**
     * author: PhucPT
     * method: save new auction with given price, timeAuction, accountId and productId
     * @param price
     * @param timeAuction
     * @param accountId
     * @param productId
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "INSERT INTO `auction` (price, time_auction, status, account_id, product_id) VALUES (:price, :timeAuction, 'in_progress', :accountId, :productId)")
    int createAuction(double price, Date timeAuction, int accountId, int productId);

    /**
     * author: PhucPT
     * method: get all auction has been submit in current auction session by product id
     * @param productId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM auction WHERE product_id = :productId  AND time_auction BETWEEN :start AND :end ORDER BY price DESC")
    Iterable<Auction> getListAuctionInProgressByProductId(int productId, Timestamp start, Timestamp end);

    /**
     * author: PhucPT
     * method: set status all auction in progress by productId
     * @param productId
     * @param status
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE `auction` SET status = :status WHERE product_id = :productId AND status = 'in_progress'")
    int setStatusAuctionInProgressByProductId(int productId, String status);

    /**
     * author: PhucPT
     * method: set status of specific auction
     * @param auctionId
     * @param status
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE `auction` SET status = :status WHERE auction_id = :auctionId")
    int setStatusAuctionByAuctionId(int auctionId, String status);

    /**
     * author: PhucPT
     * method: get highest auction of a product
     * @param productId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM `auction` WHERE product_id = :productId AND status != 'fail' AND status != 'cancel' ORDER BY price DESC LIMIT 1")
    Auction getHighestAuctionInProgressByProductId(int productId);

    /**
     * Author: unknown
     * @param pageable
     * @param id
     * @return
     */
    @Query(value = "select * from auction where auction.account_id = :id", nativeQuery = true)
    Page<Auction> findAllProductAuctionByUserId(Pageable pageable, Integer id);

    /**
     * Author: unknown
     * @param productId
     * @return
     */
    @Query(value = "SELECT * FROM auction Where product_id = ?1" , nativeQuery = true)
    List<Auction> getAllAuctionByProductId(Integer productId) ;
}
