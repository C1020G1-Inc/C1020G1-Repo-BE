package com.auction_website.model;

import java.util.List;

public class ProductDTO {
    private Product product;
    private List<ProductImage> productImageList;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }
}
