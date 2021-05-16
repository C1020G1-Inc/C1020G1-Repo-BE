package com.auction_website.dto;

import java.sql.Timestamp;

public class ProductAuctionTopDTO {
    private Integer productId;
    private String productImage;
    private String productName;
    private Double productLastPrice;
    private Timestamp productTimeEnd;

    public ProductAuctionTopDTO() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductLastPrice() {
        return productLastPrice;
    }

    public void setProductLastPrice(Double productLastPrice) {
        this.productLastPrice = productLastPrice;
    }

    public Timestamp getProductTimeEnd() {
        return productTimeEnd;
    }

    public void setProductTimeEnd(Timestamp productTimeEnd) {
        this.productTimeEnd = productTimeEnd;
    }
}
