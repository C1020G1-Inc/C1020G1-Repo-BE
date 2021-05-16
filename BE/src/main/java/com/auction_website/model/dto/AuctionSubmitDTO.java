package com.auction_website.model.dto;

import java.util.Date;

public class AuctionSubmitDTO {
    private int productId;
    private Date timeAuction;
    private double price;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getTimeAuction() {
        return timeAuction;
    }

    public void setTimeAuction(Date timeAuction) {
        this.timeAuction = timeAuction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
