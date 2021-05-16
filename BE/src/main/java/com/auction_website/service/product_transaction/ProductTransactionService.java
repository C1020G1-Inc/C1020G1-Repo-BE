package com.auction_website.service.product_transaction;

import com.auction_website.model.ProductTransaction;

import java.util.List;

public interface ProductTransactionService {
    List<ProductTransaction> showAllProductResult(Integer category);
}
