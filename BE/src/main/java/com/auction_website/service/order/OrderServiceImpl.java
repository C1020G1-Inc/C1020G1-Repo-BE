package com.auction_website.service.order;

import com.auction_website.model.Order;
import com.auction_website.model.OrderProduct;
import com.auction_website.model.Product;
import com.auction_website.model.dto.OrderDTO;
import com.auction_website.repository.OrderProductRepository;
import com.auction_website.repository.OrderRepository;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Author : CaoLPT
     * Add new order to db and return that order
     * @param orderDTO
     */
    @Override
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        List<Integer> insertedOrderId =  orderRepository.createOrder(orderDTO.getOrder().getAddress(),
                                                            orderDTO.getOrder().getPhone(),
                                                            orderDTO.getOrder().getGuide(),
                                                            orderDTO.getOrder().getTotal(),
                                                            orderDTO.getOrder().getMethodPay(),
                                                            orderDTO.getOrder().getAccount().getAccountId(),
                                                            orderDTO.getOrder().getWard().getWardId(),
                                                            orderDTO.getOrder().getUserName(),
                                                            orderDTO.getOrder().getUserEmail());

        Order newOrder = orderRepository.getOrderById(insertedOrderId.get(0));

        if(newOrder != null){
            List<OrderProduct> orderProductList = new ArrayList<>();
            for(Product product : orderDTO.getProducts()){
                // change status of product from purchasing(3) to completed(4)
                productRepository.changeProductStatus(4, product.getProductId());

                orderProductList.add(new OrderProduct(product, newOrder));
            }

            orderProductRepository.saveAll(orderProductList);
        }

        return newOrder;
    }

    /**
     * Author : CaoLPT
     * Get order by ID
     * @param orderId
     */
    @Override
    public Order getOrder(Integer orderId){
        return orderRepository.getOrderById(orderId);
    }


}
