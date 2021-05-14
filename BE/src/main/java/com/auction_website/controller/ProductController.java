package com.auction_website.controller;



import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    @Autowired
    private ProductService productService;


    @Autowired
    private ProductImageService productImageService ;

    /**
     * Author: DungHA
     * @param id
     * @return
     */
    @RequestMapping(value = "api/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.getProductByProductId(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
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
        List<ProductImage> listProduct = productImageService.showAllProductEndAuction( categoryId );
        return new ResponseEntity<>( listProduct, HttpStatus.OK );
    }
}
