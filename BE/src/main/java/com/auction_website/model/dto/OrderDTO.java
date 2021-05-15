package com.auction_website.model.dto;

import com.auction_website.model.Order;
import com.auction_website.model.Product;

import java.util.List;

public class OrderDTO {
    private Order order;

    private List<Product> products;

    public OrderDTO(Order order, List<Product> products) {
        this.order = order;
        this.products = products;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
