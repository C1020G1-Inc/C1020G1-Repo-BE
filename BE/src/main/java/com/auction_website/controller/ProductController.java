package com.auction_website.controller;

import com.auction_website.dto.ProductAuctionResultDTO;
import com.auction_website.model.ProductImage;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;

    /**
     * Method: Get all data product auction by category
     * Author: HanTH
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> showAllProductAuction(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductImage> listProduct = productImageService.showAllProductAuction( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Method: Get all data product end auction
     * Author: HanTH
     * @param categoryId
     * @return
     */
    @GetMapping("/end/category/{categoryId}")
    public ResponseEntity<?> showAllProductEndAuction(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductImage> listProduct = productImageService.showAllProductEndAuction( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: Get data result auction by category
     * Author: HanTH
     * @param categoryId
     * @return
     */
    @GetMapping("/result/category/{categoryId}")
    public ResponseEntity<?> showAllProductResult(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductAuctionResultDTO> listProduct = productService.showAllProductResult( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }
}
