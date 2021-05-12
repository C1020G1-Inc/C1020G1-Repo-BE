package com.auction_website.service.notification;

import com.auction_website.model.ProductTransaction;
import com.auction_website.model.dto.ListCurrentAuctionDTO;

public interface NotificationService {

    void updateAuctionProgress(ListCurrentAuctionDTO auctions, int productId);

    void clearAuctionProgress(int productId);

    void notifyAuctionWinner(ProductTransaction productTransaction);

    void notifyCancelTransaction(ProductTransaction productTransaction);
}
