package com.auction_website.service.notification;

import com.auction_website.model.ProductTransaction;
import com.auction_website.model.dto.ListCurrentAuctionDTO;
import com.auction_website.model.dto.MessageNotificationDTO;
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
        DatabaseReference reference = database.getReference("notification/" + productTransaction.getAccount().getAccountId()+"/winner");
        MessageNotificationDTO message = new MessageNotificationDTO();
        message.setContent("Bạn đã đấu giá thành công sản phẩm '"+productTransaction.getProduct().getProductName()+"'");
        message.setTitle("Đấu giá thành công");
        message.setUrl("/cart/"+productTransaction.getAccount().getAccountId());
        reference.push().setValueAsync(message);
    }

    @Override
    public void notifyCancelTransaction(ProductTransaction productTransaction) {
        DatabaseReference reference = database.getReference("notification/" + productTransaction.getAccount().getAccountId()+"/transaction_abort");
        MessageNotificationDTO message = new MessageNotificationDTO();
        message.setContent("Phiên giao dịch sản phẩm '"+productTransaction.getProduct().getProductName()+"' đã bị hủy bỏ");
        message.setTitle("[Cảnh báo] Giao dịch hủy bỏ");
        message.setUrl("/history/"+productTransaction.getAccount().getAccountId());
        reference.push().setValueAsync(message);
    }
}
