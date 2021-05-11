package com.auction_website.controller;

import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.dto.DetailProductDTO;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    /**
     * author: PhucPT
     * method: get product detail
     * @param productId
     * @return
     */
    @GetMapping("/detail/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable("productId") int productId) {
        Product product = productService.getProductById(productId);
        Iterable<ProductImage> productImages = productImageService.getAllImageByProductId(productId);
        return new ResponseEntity<>(new DetailProductDTO(product, productImages), HttpStatus.OK);
    }
}
