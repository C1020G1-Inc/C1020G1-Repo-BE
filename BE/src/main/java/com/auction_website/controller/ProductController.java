package com.auction_website.controller;

import com.auction_website.model.Category;
import com.auction_website.model.Product;
import com.auction_website.service.category.CategoryService;
import com.auction_website.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Author : TungNT
     * Get all product
     */
    @GetMapping("/admin/list_product")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            List<Product> productList = productService.getAllProduct();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Approved product to auction;
     */
    @PutMapping("/admin/approve/{productId}")
    public ResponseEntity<Void> approvedProduct(@PathVariable Integer productId) {
        try {
            productService.approvedProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Edit product
     */
    @PutMapping("/admin/edit_product")
    public ResponseEntity<Void> editProduct(@RequestBody Product product) {
        try {
            productService.editProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Search product multi cases
     */
    @GetMapping("/admin/search")
    public ResponseEntity<List<Product>> getProductBySearch(
            @RequestParam String productName,
            @RequestParam Integer categoryId,
            @RequestParam String userName,
            @RequestParam Integer productStatusId) {
        try {
            if (productName.equals("undefined")) {
                productName = null;
            }

            if (userName.equals("undefined")) {
                userName = null;
            }

            if (categoryId == 0) {
                categoryId = null;
            }

            if (productStatusId == 0) {
                productStatusId = null;
            }
            List<Product> productList = productService.getProductBySearch(productName, categoryId, userName, productStatusId);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Author : TungNT
     * Statistics product by month or by year
     */
    @GetMapping("/admin/statistic")
    public ResponseEntity<List<Product>> getProductByDate(
            @RequestParam(value = "monthSearch", required = false) Integer monthSearch,
            @RequestParam(value = "yearSearch", required = false) Integer yearSearch) {
        try {
            if (monthSearch == 0) {
                monthSearch = null;
            }
            List<Product> productList = productService.getProductByDate(monthSearch, yearSearch);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Get product by product's id.
     */
    @GetMapping("/admin/product/{id}")
    public ResponseEntity<Product> getIdProduct(@PathVariable Integer id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : TungNT
     * Get all category.
     */
    @GetMapping("/admin/product_category")
    public ResponseEntity<List<Category>> getAllCategory() {
        try {
            List<Category> categoryList = categoryService.getAll();
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
