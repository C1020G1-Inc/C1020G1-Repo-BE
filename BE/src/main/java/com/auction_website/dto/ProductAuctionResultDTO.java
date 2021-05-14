package com.auction_website.dto;

public class ProductAuctionResultDTO {

    private Integer productId;
    private String productName;
    private double lastPrice;
    private String userName;
    public ProductAuctionResultDTO(Integer productId, String productName, Double lastPrice, String userName) {
        this.productId = productId;
        this.productName = productName;
        this.lastPrice = lastPrice;
        this.userName = userName;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
