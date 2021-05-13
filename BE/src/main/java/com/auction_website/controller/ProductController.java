package com.auction_website.controller;

import com.auction_website.model.Category;
import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.service.category.CategoryService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
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
    public Iterable<Category> getCategory(){ return categoryService.getCategory(); }

    /**
     * author: ThinhTHB
     * method: create product & image
     * */
    @PostMapping("/create")
    public void postProduct(Product product, ProductImage image){
        productService.postProduct(product);
        productImageService.postImage(image);

    }

}
