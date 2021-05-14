package com.auction_website.controller;

import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.dto.DetailProductDTO;
import com.auction_website.model.Category;
import com.auction_website.service.category.CategoryService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/guest/category")
    public ResponseEntity<List<Category>> findAllCategory() {
        try {
            List<Category> list = categoryService.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
