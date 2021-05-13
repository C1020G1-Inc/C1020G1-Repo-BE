package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductTransactionService {
    List<ProductTransaction> getAllTransaction();

    List<ProductTransaction> getTransactionBySearch(String namePost, String nameBuy, String productName, Double price, String status);

    ProductTransaction findTransaction(Integer transactionId);

    void deleteTransaction(Integer transactionId);
}
