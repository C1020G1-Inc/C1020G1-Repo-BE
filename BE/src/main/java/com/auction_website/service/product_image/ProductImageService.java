package com.auction_website.service.product_image;

import com.auction_website.model.ProductImage;

import java.util.List;

public interface ProductImageService {

    Iterable<ProductImage> getAllImageByProductId(int productId);

    List<ProductImage> getImagesProductById(Integer productId);

    void deleteImagesById(Integer productId);

    void saveProductImage(ProductImage productImage);

    /**
     * Author: DungHA
     * @param productId
     * @return
     */
    List<ProductImage> getAllProductImageByProductId(Integer productId) ;

    /**
     * Author: HanTH
     * @param category
     * @return
     */
    List<ProductImage> showAllProductAuction(Integer category);

    /**
     * Author: HanTH
     * @param category
     * @return
     */
    List<ProductImage> showAllProductEndAuction(Integer category);
}
