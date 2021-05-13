package com.auction_website.controller;

import com.auction_website.model.Product;
import com.auction_website.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
//@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product-register/{id}")
    public ResponseEntity<Page<Product>> getAllProductRegister(@PageableDefault(size = 5) Pageable pageable,@PathVariable Integer id){
        try{
            Page<Product> product = productService.findAllProductRegister(pageable, id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product-register/update/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Integer id){
        try {
            System.out.println("abc");
            productService.updateStatus(id);
            System.out.println("abc");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product-register/update/{price}/{description}/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Double price,@PathVariable String description , @PathVariable Integer id){
        try {
            productService.updateProduct(price,description, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
