package com.auction_website.service.order;

import com.auction_website.model.Order;
import com.auction_website.model.dto.OrderDTO;

public interface OrderService {
    /**
     * Author : CaoLPT
     * Add new order to db and return that order
     * @param orderDTO
     */
    Order createOrder(OrderDTO orderDTO);

    /**
     * Author : CaoLPT
     * Get order by ID
     * @param id
     */
    Order getOrder(Integer id);
}
