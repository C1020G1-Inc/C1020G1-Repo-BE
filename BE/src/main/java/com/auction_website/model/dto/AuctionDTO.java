package com.auction_website.model.dto;

import com.auction_website.model.Auction;

import java.sql.Timestamp;

public class AuctionDTO {
    private final int auctionId;
    private final int accountId;
    private final String accountName;
    private final double price;
    private final Timestamp timeAuction;
    private final String status;

    public AuctionDTO(Auction auction) {
        this.auctionId = auction.getAuctionId();
        this.accountId = auction.getAccount().getAccountId();
        this.accountName = auction.getAccount().getAccountName();
        this.price = auction.getPrice();
        this.timeAuction = auction.getTimeAuction();
        this.status = auction.getStatus();
    }

    public int getAuctionId() {
        return auctionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getPrice() {
        return price;
    }

    public Timestamp getTimeAuction() {
        return timeAuction;
    }

    public String getStatus() {
        return status;
    }
}

