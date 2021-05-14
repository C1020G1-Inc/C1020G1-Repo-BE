package com.auction_website.model.dto;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.ProductTransaction;

import java.sql.Timestamp;

public class ProductTransactionDTO {

    private final int transactionId;
    private final DetailProductDTO detailProductDTO;
    private final String status;
    private final Timestamp transactionTime;
    private final AuctionDTO auction;

    public ProductTransactionDTO(ProductTransaction productTransaction, Iterable<ProductImage> productImages) {
        this.transactionId = productTransaction.getTransactionId();
        this.detailProductDTO = new DetailProductDTO(productTransaction.getProduct(), productImages);
        this.status = productTransaction.getStatus();
        this.transactionTime = productTransaction.getTransactionTime();
        this.auction = new AuctionDTO(productTransaction.getAuction());
    }

    public int getTransactionId() {
        return transactionId;
    }

    public DetailProductDTO getDetailProductDTO() {
        return detailProductDTO;
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
}
