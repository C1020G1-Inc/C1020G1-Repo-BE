package com.auction_website.service.notification;

import com.auction_website.model.Auction;
import com.auction_website.model.ProductTransaction;
import com.auction_website.model.dto.ListCurrentAuctionDTO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    FirebaseDatabase database;

    /**
     * author: PhucPT
     * @param auctions
     * @param productId
     */
    @Override
    public void updateAuctionProgress(ListCurrentAuctionDTO auctions, int productId) {
        DatabaseReference reference = database.getReference("auction/progress/" + productId);
        reference.setValueAsync(auctions);
    }

    /**
     * author: PhucPT
     * @param productId
     */
    @Override
    public void clearAuctionProgress(int productId) {
        DatabaseReference reference = database.getReference("auction/progress/" + productId);
        reference.removeValueAsync();
    }

    /**
     * author: PhucPT
     * @param productTransaction
     */
    @Override
    public void notifyAuctionWinner(ProductTransaction productTransaction) {
        DatabaseReference reference = database.getReference("auction/winner/" + productTransaction.getAccount().getAccountId());
        reference.push().setValueAsync(productTransaction);
    }
}
