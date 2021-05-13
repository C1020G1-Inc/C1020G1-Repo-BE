package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductTransactionService {
    Page<ProductTransaction> getAllTransaction(Pageable pageable);

    ProductTransaction findTransaction(Integer transactionId);

    void deleteTransaction(Integer transactionId);
}
