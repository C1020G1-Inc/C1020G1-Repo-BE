package com.auction_website.service.schedule;

import com.auction_website.model.Product;
import com.auction_website.model.ProductTransaction;

public interface ScheduleService {

    void endOfAuctionSchedule(int productId);

    void endOfTransactionSchedule(int productTransactionId);
}
