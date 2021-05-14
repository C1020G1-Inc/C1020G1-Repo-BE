package com.auction_website.service.product;

import com.auction_website.dto.ProductAuctionResultDTO;

import java.util.List;

public interface ProductService {
    List<ProductAuctionResultDTO> showAllProductResult(Integer categoryId);
}
