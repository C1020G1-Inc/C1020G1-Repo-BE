package com.auction_website.model.dto;

import com.auction_website.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailProductDTO {

    public static class AccountDTO {
        private int accountId;
        private String accountName;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }
    }

    public static class ImageDTO {
        private int id;
        private String image;

        public ImageDTO(int id, String imageUrl) {
            this.id = id;
            this.image = imageUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    private final Integer productId;

    private final String productName;

    private final Double price;

    private final Category category;

    private final Double priceStep;

    private final Double serviceFee;

    private final Integer quantity;

    private final Double lastPrice;

    private final String description;

    private final ProductStatus productStatus;

    private final Timestamp registerTime;

    private final Integer auctionTime;

    private final Timestamp endTime;

    private final AccountDTO account;

    private List<ImageDTO> images;

    public DetailProductDTO(Product product, Iterable<ProductImage> productImages) {

        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.priceStep = product.getPriceStep();
        this.serviceFee = product.getServiceFee();
        this.quantity = product.getQuantity();
        this.lastPrice = product.getLastPrice();
        this.description = product.getDescription();
        this.productStatus = product.getProductStatus();
        this.registerTime = product.getRegisterTime();
        this.auctionTime = product.getAuctionTime();
        this.endTime = product.getEndTime();
        this.account = new AccountDTO();
        this.account.accountId = product.getAccount().getAccountId();
        this.account.accountName = product.getAccount().getAccountName();
        if (productImages != null) {
            this.images = new ArrayList<>();
            for (ProductImage productImage : productImages) {
                this.images.add(new ImageDTO(productImage.getId(), productImage.getImage()));
            }
        }
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Double getPriceStep() {
        return priceStep;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public String getDescription() {
        return description;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public Integer getAuctionTime() {
        return auctionTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public List<ImageDTO> getImages() {
        return images;
    }
}
