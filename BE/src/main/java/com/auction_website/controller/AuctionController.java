package com.auction_website.controller;

import com.auction_website.service.auction.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auction")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;
}
