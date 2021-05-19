package com.auction_website.service.product;
import com.auction_website.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    double findCurrentStepPrice(Product product);

    Product getProductById(int productId);

    boolean setLastPrice(@Param("productId") int productId, @Param("lastPrice") double lastPrice);

    boolean setProductStatus(@Param("productId") int productId, @Param("statusId") int statusId);

    @Transactional
    boolean resetProduct(@Param("productId") int productId);

    Iterable<Product> findAllProductApproved();

    Page<Product> findAllProductRegister(Pageable pageable, Integer id);

    void updateStatus(Integer id);

    void updateProduct(Double price, String description, Integer id);

    List<Product> getAllProduct();

    List<Product> getProductBySearch(String productName , Integer categoryId , String userName , Integer productStatusId);

    List<Product> getProductByDate(Integer monthSearch , Integer yearSearch);

    void editProduct(Product product);

    void approvedProduct(Integer idProduct);

    Product getProductById(Integer productId);

    List<Product> getProductByDateForDonutChart(Integer daySearch , Integer monthSearch , Integer yearSearch);

    /**
     * author: ThinhTHB
     * method: create product
     * */
    void postProduct(Product product);

}
