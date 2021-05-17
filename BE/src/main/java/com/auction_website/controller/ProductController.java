package com.auction_website.controller;

import com.auction_website.model.Category;
import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.dto.ProductDTO;
import com.auction_website.service.category.CategoryService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    /**
     * author: ThinhTHB
     * method: get category
     * */
    @GetMapping("/category")
    public List<Category> getCategory(){ return categoryService.getCategory(); }

    /**
     * author: ThinhTHB
     * method: create product & image
     * */
    @PostMapping("/create")
    public void createProduct(@RequestBody Product product){
//        product.setAccount();
        productService.postProduct(product);
    }


    @PostMapping("/image")
    public void createImage(@RequestBody ProductImage image){
        productImageService.postImage(image);
    }
}
