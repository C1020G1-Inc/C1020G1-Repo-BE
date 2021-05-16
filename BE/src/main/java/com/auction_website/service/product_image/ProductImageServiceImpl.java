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
     * Method: Get all data product auction by category.
     * Author: HanTH
     *
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
     * Method: Get all data product end auction by category
     * Author: HanTH
     *
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

    /**
     * Method: Get Image of product auction through id
     * Author: HanTH
     *
     * @param productId
     * @return
     */
    @Override
    public ProductImage findOneByProductId(Integer productId) {
        return productImageRepository.findOneByProductId( productId );
    }

    /**
     * Method: Search product auction by name , category , price range
     * Author: HanTH
     *
     * @param keySearch
     * @param category
     * @param priceRange
     * @return
     */
    @Override
    public List<ProductImage> searchProductAuction(String keySearch, Integer category, Integer priceRange) {
        if (priceRange == 0) {
            switch (category) {
                case 0:
                    return productImageRepository.searchProductAuctionByName( keySearch );
                case 4:
                    return productImageRepository.searchProductAuctionByHotAll( keySearch );
                default:
                    return productImageRepository.searchProductAuctionByNameAndCategory( keySearch, category );
            }
        } else {
            int prevPriceRange = 0;
            int lastPriceRange = 0;
            switch (priceRange) {
                case 1:
                    prevPriceRange = 0;
                    lastPriceRange = 500000;
                    break;
                case 2:
                    prevPriceRange = 500000;
                    lastPriceRange = 2000000;
                    break;
                case 3:
                    prevPriceRange = 2000000;
                    lastPriceRange = 10000000;
                    break;
                case 4:
                    prevPriceRange = 10000000;
                    lastPriceRange = 50000000;
                    break;
                case 5:
                    return productImageRepository.searchProductAuctionByNameAndPriceMax( keySearch );
                default:
            }
            switch (category) {
                case 0:
                    return productImageRepository.searchProductAuctionByNameAndPrice( keySearch, prevPriceRange, lastPriceRange );
                case 4:
                    return productImageRepository.searchProductAuctionByHotAndPrice( keySearch, prevPriceRange, lastPriceRange );
                default:
                    return productImageRepository.searchProductAuctionByAll( keySearch, category, prevPriceRange, lastPriceRange );
            }
        }
    }
}
