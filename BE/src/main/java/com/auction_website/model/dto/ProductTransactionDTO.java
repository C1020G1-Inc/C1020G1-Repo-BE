package com.auction_website.model.dto;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.ProductTransaction;

import java.sql.Timestamp;

public class ProductTransactionDTO {

    private final int transactionId;
    private final Product product;
    private final String status;
    private final Timestamp transactionTime;
    private final AuctionDTO auction;
    private final Iterable<ProductImage> images;
    public ProductTransactionDTO(ProductTransaction productTransaction, Iterable<ProductImage> productImages) {
        this.transactionId = productTransaction.getTransactionId();
        this.product = productTransaction.getProduct();
        this.images = productImages;
        this.status = productTransaction.getStatus();
        this.transactionTime = productTransaction.getTransactionTime();
        this.auction = new AuctionDTO(productTransaction.getAuction());
    }
    public int getTransactionId() {
        return transactionId;
    }
    public Product getProduct() {
        return product;
    }
    public String getStatus() {
        return status;
    }
    public Timestamp getTransactionTime() {
        return transactionTime;
    }
    public AuctionDTO getAuction() {
        return auction;
    }
    public Iterable<ProductImage> getImages() {
        return images;
    }
}
