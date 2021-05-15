package com.auction_website.controller;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductImage;
import com.auction_website.model.ProductTransaction;
import com.auction_website.model.dto.AuctionSubmitDTO;
import com.auction_website.model.dto.ListCurrentAuctionDTO;
import com.auction_website.model.dto.ProductTransactionDTO;
import com.auction_website.service.account.AccountService;
import com.auction_website.service.auction.AuctionService;
import com.auction_website.service.notification.NotificationService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_image.ProductImageService;
import com.auction_website.service.product_transaction.ProductTransactionService;
import com.auction_website.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auction")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTransactionService productTransactionService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private AccountService accountService;

    /**
     * author: PhucPT
     * method: create new auction with information from client
     *
     * @param auctionSubmit
     * @return
     */
    @PostMapping("bidding")
    @Transactional
    public ResponseEntity<?> setNewAuction(@RequestBody AuctionSubmitDTO auctionSubmit, Principal principal) {
        try {
            String accountName = principal.getName();
            int accountId = accountService.findByAccountName(accountName).getAccountId();

            Product product = productService.getProductById(auctionSubmit.getProductId());
            double currentPrice = (product.getLastPrice() == null) ? product.getPrice() : product.getLastPrice();
            double currentStep = productService.findCurrentStepPrice(product);
            Map<String, Object> resultMap = new HashMap<>();

            if (auctionSubmit.getPrice() < currentPrice + currentStep || auctionSubmit.getPrice() % currentStep != 0) {
                resultMap.put("error", "wrong_price");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }
            if (new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000).before(auctionSubmit.getTimeAuction())) {
                resultMap.put("error", "auction_expired");
                return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
            }

            auctionService.createAuction(auctionSubmit.getPrice(), auctionSubmit.getTimeAuction(), accountId, auctionSubmit.getProductId());
            productService.setLastPrice(auctionSubmit.getProductId(), auctionSubmit.getPrice());

            Iterable<Auction> auctions = auctionService.getListAuctionInProgressByProductId(product);
            product.setLastPrice(auctionSubmit.getPrice());
            currentStep = productService.findCurrentStepPrice(product);

            notificationService.updateAuctionProgress(new ListCurrentAuctionDTO(currentStep, auctions), auctionSubmit.getProductId());
            resultMap.put("message", "success");
            return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: CuongNVM
     * list product auction
     */
    @GetMapping("/product-auction/{id}")
    public ResponseEntity<Page<Auction>> getAllProductAuction(@PageableDefault(size = 5) Pageable pageable, @PathVariable Integer id) {
        try {
            Page<Auction> auctions = auctionService.findAllProductAuction(pageable, id);
            return new ResponseEntity<>(auctions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * author: PhucPT
     *
     * @param productId
     * @return
     */
    @GetMapping("list/{product}")
    public ResponseEntity<?> getListCurrentAuctionByProductId(@PathVariable("product") int productId) {
        try {
            Product product = productService.getProductById(productId);
            Iterable<Auction> auctions = auctionService.getListAuctionInProgressByProductId(product);
            ListCurrentAuctionDTO currentAuctions = new ListCurrentAuctionDTO(productService.findCurrentStepPrice(product), auctions);
            return new ResponseEntity<>(currentAuctions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * author: PhucPT
     * method: check approved product at app start and schedule task when auction end
     */
    @EventListener(ContextRefreshedEvent.class)
    public void schedulingEndApprovedProduct() {
        Iterable<Product> products = productService.findAllProductApproved();
        for (Product product : products) {
            Iterable<Auction> auctions = auctionService.getListAuctionInProgressByProductId(product);
            ListCurrentAuctionDTO currentAuctions = new ListCurrentAuctionDTO(productService.findCurrentStepPrice(product), auctions);
            notificationService.updateAuctionProgress(currentAuctions, product.getProductId());
            scheduleService.endOfAuctionSchedule(product.getProductId());
        }
    }

    /**
     * author: PhucPT
     * method: schedule task when transaction end
     */
    @EventListener(ContextRefreshedEvent.class)
    public void schedulingEndTransaction() {
        Iterable<ProductTransaction> productTransactions = productTransactionService.findAllPurchasingTransaction();
        for (ProductTransaction productTransaction : productTransactions) {
            scheduleService.endOfTransactionSchedule(productTransaction.getTransactionId());
        }
    }

    /**
     * author: PhucPT
     * method: get all transaction in purchasing
     *
     * @return
     */
    @GetMapping("/cart")
    public ResponseEntity<?> getTransactionInPurchasing(Principal principal) {
        try {
            String accountName = principal.getName();
            int accountId = accountService.findByAccountName(accountName).getAccountId();
            Iterable<ProductTransaction> productTransactions = productTransactionService.getCurrentTransactionByAccountId(accountId);
            List<ProductTransactionDTO> productTransactionDTOList = new ArrayList<>();
            for (ProductTransaction productTransaction : productTransactions) {
                Iterable<ProductImage> productImages = productImageService.getAllImageByProductId(productTransaction.getProduct().getProductId());
                productTransactionDTOList.add(new ProductTransactionDTO(productTransaction, productImages));
            }
            return new ResponseEntity<>(productTransactionDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author: Unknown
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    public ResponseEntity<List<Auction>> getAllPostInWallUser(@PathVariable("productId") Integer productId) {
        List<Auction> auctionList = auctionService.getAllAuctionByProductId(productId);

        if (auctionList == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(auctionList, HttpStatus.OK);
    }
}
