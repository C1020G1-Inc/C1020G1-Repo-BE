package com.auction_website.controller;

import com.auction_website.model.Order;
import com.auction_website.model.dto.OrderDTO;
import com.auction_website.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Author : CaoLPT
     * API to add new order
     * @param orderDTO
     */
    @PostMapping("")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult){
        try{
            if (bindingResult.hasErrors())
                return new ResponseEntity<>( HttpStatus.BAD_REQUEST );

            Order newOrder =  orderService.createOrder(orderDTO);
            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : CaoLPT
     * API to get order by ID
     * @param id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id){
        try{
            return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
