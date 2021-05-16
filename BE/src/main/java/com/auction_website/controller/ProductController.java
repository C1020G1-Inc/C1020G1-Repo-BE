package com.auction_website.controller;

import com.auction_website.dto.ProductAuctionResultDTO;
import com.auction_website.dto.ProductAuctionTopDTO;
import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.ProductTransaction;
import com.auction_website.service.auction.AuctionService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import com.auction_website.service.product_transaction.ProductTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private ProductTransactionService productTransactionService;
    @Autowired
    private AuctionService auctionService;

    /**
     * Method: Get all data product auction by category
     * Author: HanTH
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> showAllProductAuction(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductImage> listProduct = productImageService.showAllProductAuction( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Method: Get all data product end auction
     * Author: HanTH
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/end/category/{categoryId}")
    public ResponseEntity<?> showAllProductEndAuction(@PathVariable("categoryId") Integer categoryId) {
        try {
            List<ProductImage> listProduct = productImageService.showAllProductEndAuction( categoryId );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: Get data result auction by category
     * Author: HanTH
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/result/category/{categoryId}")
    public ResponseEntity<?> showAllProductResult(@PathVariable Integer categoryId) {
        try {
            List<ProductAuctionResultDTO> listProduct = new ArrayList<>();
            List<ProductTransaction> listProductTrans = productTransactionService.showAllProductResult( categoryId );
            ProductAuctionResultDTO productAuctionResultDTO = null;
            for (ProductTransaction listProductTran : listProductTrans) {
                productAuctionResultDTO = new ProductAuctionResultDTO();
                productAuctionResultDTO.setProductId( listProductTran.getProduct().getProductId() );
                productAuctionResultDTO.setLastPrice( listProductTran.getProduct().getLastPrice() );
                productAuctionResultDTO.setProductName( listProductTran.getProduct().getProductName() );
                productAuctionResultDTO.setUserName( listProductTran.getAccount().getUser().getUserName() );
                productAuctionResultDTO.setProductImage( productImageService.findOneByProductId( productAuctionResultDTO.getProductId() ).getImage() );
                listProduct.add( productAuctionResultDTO );
            }
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }

    }

    /**
     * Method: search product auction
     * Author: HanTH
     * @param keySearch
     * @param category
     * @param priceRange
     * @return
     */

    @GetMapping("/search")
    public ResponseEntity<?> searchProductAuction(@RequestParam("keySearch") String keySearch,
                                                  @RequestParam("category") Integer category,
                                                  @RequestParam("priceRange") Integer priceRange) {
        try {
            List<ProductImage> listProduct = productImageService.searchProductAuction( keySearch, category, priceRange );
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }

    /**
     * Method: get top 10 product auction .
     * Author: HanTH
     * @param category
     * @return
     */
    @GetMapping("/top/{category}")
    public ResponseEntity<?> showTopProductAuction(@PathVariable Integer category) {
        try {
            List<ProductAuctionTopDTO> listProduct = new ArrayList<>();
            List<Auction> auctionList = auctionService.showTopProductAuction( category );
            ProductAuctionTopDTO productAuctionTopDTO = null;
            for (Auction auction : auctionList) {
                productAuctionTopDTO = new ProductAuctionTopDTO();
                productAuctionTopDTO.setProductId( auction.getProduct().getProductId() );
                productAuctionTopDTO.setProductName( auction.getProduct().getProductName() );
                productAuctionTopDTO.setProductLastPrice( auction.getProduct().getLastPrice() );
                productAuctionTopDTO.setProductTimeEnd( auction.getProduct().getEndTime() );
                productAuctionTopDTO.setProductImage( productImageService.findOneByProductId( auction.getProduct().getProductId() ).getImage() );
                listProduct.add( productAuctionTopDTO );
            }
            return new ResponseEntity<>( listProduct, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
    }
}
