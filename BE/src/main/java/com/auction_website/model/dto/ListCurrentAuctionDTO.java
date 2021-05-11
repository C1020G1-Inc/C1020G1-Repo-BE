package com.auction_website.model.dto;

import com.auction_website.model.Auction;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCurrentAuctionDTO {

    private static class AuctionDTO {
        private final int auctionId;
        private final int accountId;
        private final String accountName;
        private final double price;
        private final Timestamp timeAuction;
        private final String status;

        public AuctionDTO(int auctionId, int accountId, String accountName, double price, Timestamp timeAuction, String status) {
            this.auctionId = auctionId;
            this.accountId = accountId;
            this.accountName = accountName;
            this.price = price;
            this.timeAuction = timeAuction;
            this.status = status;
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

    private final double currentStep;
    private List<AuctionDTO> auctions;

    public double getCurrentStep() {
        return currentStep;
    }

    public List<AuctionDTO> getAuctions() {
        return auctions;
    }

    public ListCurrentAuctionDTO(double currentStep, Iterable<Auction> auctions) {
        this.currentStep = currentStep;
        if (auctions != null) {
            this.auctions = new ArrayList<>();
            for (Auction auction : auctions) {
                this.auctions.add(new AuctionDTO(auction.getAuctionId(), auction.getAccount().getAccountId(), auction.getAccount().getAccountName(), auction.getPrice(), auction.getTimeAuction(), auction.getStatus()));
            }
        }
    }
}
