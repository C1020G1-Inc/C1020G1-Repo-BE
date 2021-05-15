package com.auction_website.controller;

import com.auction_website.model.Auction;
import com.auction_website.service.auction.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    /**
     * Author: CuongNVM
     * list product auction
     */
    @GetMapping("/product-auction/{id}")
    public ResponseEntity<Page<Auction>> getAllProductAuction(@PageableDefault(size = 5)Pageable pageable, @PathVariable Integer id){
        try{
            Page<Auction> auctions = auctionService.findAllProductAuction(pageable, id);
            return new ResponseEntity<>(auctions, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/auction/{productId}")
    public ResponseEntity<List<Auction>> getAllPostInWallUser(@PathVariable("productId") Integer productId){
        List<Auction> auctionList = auctionService.getAllAuctionByProductId(productId);

        if(auctionList == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(auctionList, HttpStatus.OK);
    }
}
