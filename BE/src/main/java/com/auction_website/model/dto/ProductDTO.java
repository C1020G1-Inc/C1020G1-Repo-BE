package com.auction_website.model.dto;

import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;

public class ProductDTO {

    private Product product;

    private ProductImage image;

    public Product getDTOProduct() {
        return product;
    }

    public void setDTOProduct(Product product) {
        this.product = product;
    }

    public ProductImage getDTOImage() {
        return image;
    }

    public void setDTOImage(ProductImage image) {
        this.image = image;
    }
}
