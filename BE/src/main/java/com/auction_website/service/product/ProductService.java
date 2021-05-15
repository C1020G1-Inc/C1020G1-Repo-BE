package com.auction_website.service.product;
import com.auction_website.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAllProductRegister(Pageable pageable, Integer id);


    void updateStatus(Integer id);
    void updateProduct(Double price, String description, Integer id);
//    Page<Product> searchProductRegister(String name,String time,Double price, Pageable pageable);

    List<Product> getAllProduct();
    List<Product> getProductBySearch(String productName , Integer categoryId , String userName , Integer productStatusId);
    List<Product> getProductByDate(Integer monthSearch , Integer yearSearch);
    void editProduct(Product product);
    void approvedProduct(Integer idProduct);
    Product getProductById(Integer productId);

}
