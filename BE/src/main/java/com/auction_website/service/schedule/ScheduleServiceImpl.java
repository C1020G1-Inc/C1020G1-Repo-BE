package com.auction_website.service.schedule;
import com.auction_website.model.Auction;
import com.auction_website.model.Product;
import com.auction_website.model.ProductTransaction;
import com.auction_website.service.account.AccountService;
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
import java.sql.Timestamp;
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
    @Autowired
    AccountService accountService;
    /**
     * author: PhucPT
     * method: schedule end of auction task
     *
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
                    Timestamp startTime = new Timestamp(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000);
                    productTransactionService.createProductTransaction(productId, auction.getAccount().getAccountId(), auction.getAuctionId(), startTime);
                    ProductTransaction productTransaction = productTransactionService.findCurrentTransactionByProductId(productId);
                    endOfTransactionSchedule(productTransaction.getTransactionId());
                    notificationService.notifyAuctionWinner(productTransaction);
                    notificationService.clearAuctionProgress(productId);
                    emailService.sendMailToWinner(productTransaction);
                } else {
                    productService.resetProduct(productId);
                }
            }
        }, new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000));
        System.out.println(new Date(product.getRegisterTime().getTime() + (long) product.getAuctionTime() * 60 * 1000));
    }
    /**
     * author: PhucPT
     * method: schedule end of transaction task
     *
     * @param productTransactionId
     */
    @Override
    public void endOfTransactionSchedule(int productTransactionId) {
        ProductTransaction productTransaction = productTransactionService.getTransactionById(productTransactionId);
        taskScheduler.schedule(new Runnable() {
            @Override
            @Transactional
            public void run() {
                if (productTransaction.getStatus().equals("purchasing")) {
                    productTransactionService.setStatusByTransactionId("fail", productTransactionId);
                    auctionService.setStatusAuctionByAuctionId(productTransaction.getAuction().getAuctionId(), "cancel");
                    auctionService.setStatusAuctionInProgressByAccountId(productTransaction.getAccount().getAccountId(),productTransaction.getAuction().getAuctionId(), "fail");
                    int numberOfCancelAuctionByAccount = productTransactionService.getNumberOfCancelTransactionByAccount(productTransaction.getAccount().getAccountId());
                    System.out.println(numberOfCancelAuctionByAccount);
                    if (numberOfCancelAuctionByAccount == 3) {
                        accountService.lockUserById(productTransaction.getAccount().getAccountId());
                        emailService.sendEmailLockUserByOverCancelTransaction(productTransaction.getAccount());
                    } else {
                        notificationService.notifyCancelTransaction(productTransaction);
                        emailService.sendEmailCancelTransaction(productTransaction, numberOfCancelAuctionByAccount);
                    }
                    Auction auction = auctionService.getHighestAuctionInProgressByProductId(productTransaction.getProduct().getProductId());
                    int numberOfFailAuction = auctionService.getNumberOfFailAuctionCurrent(productTransaction.getProduct());
                    if (numberOfFailAuction == 3 || auction == null) {
                        productService.setProductStatus(productTransaction.getProduct().getProductId(), 5);
                        auctionService.setStatusAuctionInProgressByProductId(productTransaction.getProduct().getProductId(), "fail");
                    } else {
                        Timestamp startTime = new Timestamp(productTransaction.getTransactionTime().getTime()+  60 * 1000 *2);
                        productTransactionService.createProductTransaction(productTransaction.getProduct().getProductId(), auction.getAccount().getAccountId(), auction.getAuctionId(), startTime);
                        ProductTransaction newProductTransaction = productTransactionService.findCurrentTransactionByProductId(productTransaction.getProduct().getProductId());
                        endOfTransactionSchedule(newProductTransaction.getTransactionId());
                        notificationService.notifyAuctionWinner(newProductTransaction);
                        emailService.sendMailToWinner(newProductTransaction);
                    }
                }
            }
        }, new Date(productTransaction.getTransactionTime().getTime() + 60 * 1000 * 2));
        taskScheduler.schedule(() -> {
            notificationService.notifyNearEndPurchasing(productTransaction);
            emailService.sendEmailNearEndTransaction(productTransaction);
        }, new Date(productTransaction.getTransactionTime().getTime() + 60 * 1000));
    }
}
