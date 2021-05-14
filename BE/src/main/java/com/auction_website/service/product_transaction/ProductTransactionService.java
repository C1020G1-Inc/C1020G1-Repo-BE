package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductTransactionService {
    /**
     * Author: DungNv
     * @return
     */
    List<ProductTransaction> getAllTransaction();

    /**
     * Author: DungNV
     * @param namePost
     * @param nameBuy
     * @param productName
     * @param price
     * @param status
     * @return
     */
    List<ProductTransaction> getTransactionBySearch(String namePost, String nameBuy, String productName, Double price, String status);

    /**
     * Author: DungNV
     * @param transactionId
     * @return
     */
    ProductTransaction findTransaction(Integer transactionId);

    /**
     * Author: DungNV
     * @param transactionId
     */
    void deleteTransaction(Integer transactionId);
}
