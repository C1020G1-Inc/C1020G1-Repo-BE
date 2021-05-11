package com.auction_website.controller;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductTransaction;
import com.auction_website.model.dto.AuctionSubmitDTO;
import com.auction_website.model.dto.ListCurrentAuctionDTO;
import com.auction_website.service.auction.AuctionService;
import com.auction_website.service.notification.NotificationService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_transaction.ProductTransactionService;
import com.auction_website.service.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * author: PhucPT
     * method: create new auction with information from client
     * @param auctionSubmit
     * @return
     */
    @PostMapping("bidding")
    @Transactional
    public ResponseEntity<?> setNewAuction(@RequestBody AuctionSubmitDTO auctionSubmit) {
        int accountId = 1;

        Product product = productService.getProductById(auctionSubmit.getProductId());
        double currentPrice = (product.getLastPrice() == null) ? product.getPrice() : product.getLastPrice();
        double currentStep = productService.findCurrentStepPrice(product);
        Map<String, Object> resultMap = new HashMap<>();

        if (auctionSubmit.getPrice() < currentPrice + currentStep || auctionSubmit.getPrice() % currentStep != 0) {
            resultMap.put("error", "wrong_price");
            return new ResponseEntity<>(resultMap, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        System.out.println(new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 60 * 1000));
        System.out.println(auctionSubmit.getTimeAuction());
        if (new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 *60 * 1000).before(auctionSubmit.getTimeAuction())) {
            resultMap.put("error", "auction_expired");
            return new ResponseEntity<>(resultMap, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        auctionService.createAuction(auctionSubmit.getPrice(), auctionSubmit.getTimeAuction(), accountId, auctionSubmit.getProductId());
        productService.setLastPrice(auctionSubmit.getProductId(), auctionSubmit.getPrice());

        Iterable<Auction> auctions = auctionService.getListAuctionInProgressByProductId(auctionSubmit.getProductId());
        product.setLastPrice(auctionSubmit.getPrice());
        currentStep = productService.findCurrentStepPrice(product);

        notificationService.updateAuctionProgress(new ListCurrentAuctionDTO(currentStep, auctions), auctionSubmit.getProductId());
        resultMap.put("message", "success");
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }

    /**
     * author: PhucPT
     * @param productId
     * @return
     */
    @GetMapping("list/{product}")
    public ResponseEntity<?> getListCurrentAuctionByProductId(@PathVariable("product") int productId) {
        Iterable<Auction> auctions = auctionService.getListAuctionInProgressByProductId(productId);
        Product product = productService.getProductById(productId);
        ListCurrentAuctionDTO currentAuctions = new ListCurrentAuctionDTO(productService.findCurrentStepPrice(product), auctions);
        return new ResponseEntity<>(currentAuctions, HttpStatus.OK);
    }


    /**
     * author: PhucPT
     * method: check approved product at app start and schedule task when auction end
     */
    @EventListener(ContextRefreshedEvent.class)
    public void schedulingEndApprovedProduct() {
        Iterable<Product> products = productService.findAllProductApproved();
        for (Product product : products) {
            scheduleService.endOfAuctionSchedule(product.getProductId());
        }
    }

    /**
     * author: PhucPT
     * method: check transaction in progress at app start and schedule task when transaction end
     */
    @EventListener(ContextRefreshedEvent.class)
    public void schedulingEndTransaction() {
        Iterable<ProductTransaction> productTransactions = productTransactionService.findAllPurchasingTransaction();
        for (ProductTransaction productTransaction : productTransactions) {
            scheduleService.endOfTransactionSchedule(productTransaction.getTransactionId());
        }
    }
}
