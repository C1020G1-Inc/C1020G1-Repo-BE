package com.auction_website.service.schedule;


public interface ScheduleService {

    void endOfAuctionSchedule(int productId);

    void endOfTransactionSchedule(int productTransactionId);
}
