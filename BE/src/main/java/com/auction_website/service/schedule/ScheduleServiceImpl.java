package com.auction_website.service.schedule;

import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductTransaction;
import com.auction_website.service.auction.AuctionService;
import com.auction_website.service.email.EmailService;
import com.auction_website.service.notification.NotificationService;
import com.auction_website.service.product.ProductService;
import com.auction_website.service.product_transaction.ProductTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    TaskScheduler taskScheduler;
    @Autowired
    AuctionService auctionService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductTransactionService productTransactionService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    EmailService emailService;

    /**
     * author: PhucPT
     * @param productId
     */
    @Override
    public void endOfAuctionSchedule(int productId) {
        Product product = productService.getProductById(productId);
        taskScheduler.schedule(new Runnable() {
            @Override
            @Transactional
            public void run() {
                Auction auction = auctionService.getHighestAuctionInProgressByProductId(productId);
                if (auction != null) {
                    productService.setProductStatus(productId, 3);
                    auctionService.setStatusAuctionByAuctionId(auction.getAuctionId(), "win");
                    auctionService.setStatusAuctionInProgressByProductId(productId, "fail");
                    productTransactionService.createProductTransaction(productId, auction.getAccount().getAccountId());
                    ProductTransaction productTransaction = productTransactionService.findCurrentTransactionByProductId(productId);
                    endOfTransactionSchedule(productTransaction.getTransactionId());
                    notificationService.notifyAuctionWinner(productTransaction);
                    notificationService.clearAuctionProgress(productId);
                    try {
                        emailService.sendMailToWinner(productTransaction);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                } else {
                    productService.resetProduct(productId);
                }
            }
        }, new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000));
        System.out.println(new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000));

    }

    /**
     * author: PhucPT
     * @param productTransactionId
     */
    @Override
    public void endOfTransactionSchedule(int productTransactionId) {
        ProductTransaction productTransaction = productTransactionService.getTransactionById(productTransactionId);
        taskScheduler.schedule(new Runnable() {
            @Override
            @Transactional
            public void run() {
                if(productTransaction.getStatus().equals("purchasing")){
                    productTransactionService.setStatusByTransactionId("fail",productTransactionId);
                    productService.setProductStatus(productTransaction.getProduct().getProductId(),4);
                    auctionService.setStatusAuctionByAuctionId(auctionService.getHighestAuctionInProgressByProductId(productTransaction.getProduct().getProductId()).getAuctionId(),"cancel");
                }
            }
        }, new Date(productTransaction.getProduct().getRegisterTime().getTime() + (long) productTransaction.getProduct().getAuctionTime() * 60 * 1000 + 10 * 60 * 1000));
    }
}
