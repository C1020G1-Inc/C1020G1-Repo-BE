package com.auction_website.controller;


import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.Category;
import com.auction_website.service.category.CategoryService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;
    /**
     * Author : SonPH
     * find product by productId
     *
     * @param productId
     */
    @GetMapping("api/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    /**
     * Author: DungHA
     * @param productId
     * @return
     */
    @GetMapping("/api/productImage/{productId}")
    public ResponseEntity<List<ProductImage>> getAllProductImage(@PathVariable("productId") Integer productId){
        List<ProductImage> productImages = productImageService.getAllProductImageByProductId(productId);

        if(productImages == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }


    /**
     * Author: HanTH
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> showAllProductAuction(@PathVariable("categoryId") Integer categoryId) {
        List<ProductImage> listProduct = productImageService.showAllProductAuction( categoryId );
        return new ResponseEntity<>( listProduct, HttpStatus.OK );
    }

    /**
     * Author: HanTH
     * @param categoryId
     * @return
     */
    @GetMapping("/end/category/{categoryId}")
    public ResponseEntity<?> showAllProductEndAuction(@PathVariable("categoryId") Integer categoryId) {
        List<ProductImage> listProduct = productImageService.showAllProductEndAuction(categoryId);
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
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
