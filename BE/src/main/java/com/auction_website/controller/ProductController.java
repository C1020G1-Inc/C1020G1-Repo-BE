package com.auction_website.controller;

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
    @GetMapping("/{category}")
    public ResponseEntity<?> showAllProductAuction(@PathVariable("category") Integer category) {
        List<ProductImage> listProduct = productImageService.showAllProductAuction( category );
        if (listProduct.isEmpty()) {
            ProductImage productImage = new ProductImage();
            listProduct.add( productImage );
            return new ResponseEntity<>( listProduct, HttpStatus.BAD_REQUEST );
        }
        return new ResponseEntity<>( listProduct, HttpStatus.OK );
    }
}
