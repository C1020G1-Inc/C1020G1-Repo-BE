package com.auction_website.service.product_status;

import com.auction_website.repository.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStatusServiceImpl implements ProductStatusService{
    @Autowired
    private ProductStatusRepository productStatusRepository;
}
