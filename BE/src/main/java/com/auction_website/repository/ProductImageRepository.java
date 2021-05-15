package com.auction_website.repository;

import com.auction_website.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    /**
     * Author: DungHA
     * @param productId
     * @return
     */
    @Query(value = "SELECT * FROM product_image Where product_id = ?1" , nativeQuery = true)
    List<ProductImage> getAllProductImageByProductId(Integer productId) ;

    /**
     * Author: HanTH
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where p.product_status_id = 2\n" +
            "group by  p.product_id\n" +
            "order by p.product_register_time desc", nativeQuery = true)
    List<ProductImage> showAllProductAuction();

    /**
     * Author: HanTH
     * @param category
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "WHERE (p.category_id = ?1 and p.product_status_id = 2)\n" +
            "group by  p.product_id\n" +
            "order by p.product_register_time desc;", nativeQuery = true)
    List<ProductImage> showAllProductAuctionByCategory(Integer category);


    /**
     * Author: HanTH
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "WHERE (p.product_price > 10000000 and p.product_status_id = 2)\n" +
            "group by  p.product_id\n" +
            "order by p.product_register_time desc", nativeQuery = true)
    List<ProductImage> showAllProductAuctionHot();

    /**
     * Author: HanTH
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where p.product_status_id = 3\n" +
            "group by  p.product_id", nativeQuery = true)
    List<ProductImage> showAllProductEndAuction();


    /**
     * Author: HanTH
     * @param category
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where (p.category_id = ?1 and p.product_status_id = 3)\n" +
            "group by  p.product_id", nativeQuery = true)
    List<ProductImage> showAllProductEndAuctionByCategory(Integer category);


    /**
     * Author: HanTH
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where (p.product_price > 10000000 and p.product_status_id = 3)\n" +
            "group by  p.product_id\n" +
            "order by p.product_register_time desc", nativeQuery = true)
    List<ProductImage> showAllProductEndAuctionHot();
}
