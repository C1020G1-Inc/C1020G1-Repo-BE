package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;
import com.auction_website.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    /**
     * Author : DungHA
     * @param productId
     * @return
     */
    @Override
    public List<ProductImage> getAllProductImageByProductId(Integer productId) {
        return productImageRepository.getAllProductImageByProductId(productId);
    }

    /**
     * Author: HanTH
     * @param category
     * @return
     */
    @Override
    public List<ProductImage> showAllProductAuction(Integer category) {
        switch (category) {
            case 0:
                return productImageRepository.showAllProductAuction();
            case 4:
                return productImageRepository.showAllProductAuctionHot();
            default:
                return productImageRepository.showAllProductAuctionByCategory( category );
        }
    }


    /**
     * Author: HanTH
     * @param category
     * @return
     */
    @Override
    public List<ProductImage> showAllProductEndAuction(Integer category) {
        switch (category) {
            case 0:
                return productImageRepository.showAllProductEndAuction();
            case 4:
                return productImageRepository.showAllProductEndAuctionHot();
            default:
                return productImageRepository.showAllProductEndAuctionByCategory( category );
        }
    }
}
