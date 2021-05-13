package com.auction_website.model.dto;

import com.auction_website.model.Auction;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCurrentAuctionDTO {

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
                this.auctions.add(new AuctionDTO(auction));
            }
        }
    }
}
