package com.auction_website.repository;

import com.auction_website.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    /**
     * author: PhucPT
     * method: get all product image by product id
     * @param productId
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM product_image WHERE product_id = :productId")
    Iterable<ProductImage> getAllImageByProductId(int productId);

    /**
     * Author: TungNT
     * @param productId
     * @return
     */
    @Query("select productImage from ProductImage productImage " +
            "where productImage.product.productId = :productId")
    List<ProductImage> getAllProductImageByProductId(Integer productId);


    @Transactional
    @Modifying
    @Query("delete from ProductImage productImage " +
            "where productImage.product.productId = :productId")
    void deleteByProductId(Integer productId);


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
            "WHERE (p.product_last_price > 10000000 and p.product_status_id = 2)\n" +
            "group by  p.product_id\n" +
            "order by p.product_register_time desc", nativeQuery = true)
    List<ProductImage> showAllProductAuctionHot();

    /**
     * Author: HanTH
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where (p.product_status_id = 3 or p.product_status_id = 4 or p.product_status_id = 5)\n" +
            "group by  p.product_id", nativeQuery = true)
    List<ProductImage> showAllProductEndAuction();


    /**
     * Author: HanTH
     * @param category
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where (p.category_id = ?1 and (p.product_status_id = 3 or p.product_status_id = 4 or p.product_status_id = 5))\n" +
            "group by  p.product_id", nativeQuery = true)
    List<ProductImage> showAllProductEndAuctionByCategory(Integer category);


    /**
     * Author: HanTH
     * @return
     */
    @Query(value = "select * from auction_website.product_image img\n" +
            "right join product p on p.product_id = img.product_id\n" +
            "where (p.product_price > 10000000 and (p.product_status_id = 3 or p.product_status_id = 4 or p.product_status_id = 5))\n" +
            "group by  p.product_id\n" +
            "order by p.product_register_time desc", nativeQuery = true)
    List<ProductImage> showAllProductEndAuctionHot();

    @Query(value = "select * from product_image where product_id = ?1 limit 1", nativeQuery = true)
    ProductImage findOneByProductId(Integer id);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%') and p.category_id = ?2 and p.product_last_price between ?3 and ?4 )\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByAll(String keySearch, Integer categoryId, int prevPriceRange, int lastPriceRange);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%')and p.product_last_price between ?2 and ?3 )\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByNameAndPrice(String keySearch, int prevPriceRange, int lastPriceRange);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%')and p.product_last_price > 50000000)\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByNameAndPriceMax(String keySearch);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%') and p.category_id = ?2)\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByNameAndCategory(String keySearch, Integer categoryId);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%'))\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByName(String keySearch);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%') and p.product_last_price>5000000)\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByHotAll(String keySearch);

    @Query(value = "select * from auction_website.product_image img\n" +
            "join auction_website.product p on img.product_id = p.product_id\n" +
            "where (p.product_status_id = 2 and p.product_name like concat('%',?1, '%') and p.product_last_price>5000000 and p.product_last_price between ?2 and ?3 )\n" +
            "group by p.product_id", nativeQuery = true)
    List<ProductImage> searchProductAuctionByHotAndPrice(String keySearch, int prevPriceRange, int lastPriceRange);

}
