package com.auction_website.service.product;

import com.auction_website.dto.ProductAuctionResultDTO;
import com.auction_website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductAuctionResultDTO> showAllProductResult(Integer categoryId) {
        switch (categoryId) {
            case 0:
                return productRepository.showAllProductResult();
            case 4:
                return productRepository.showAllProductResultByHot();
            default:
                return productRepository.showAllProductResultByCategory( categoryId );
        }
    }
}
