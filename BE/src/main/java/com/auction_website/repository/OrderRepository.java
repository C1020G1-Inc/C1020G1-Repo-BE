package com.auction_website.repository;

import com.auction_website.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Author : CaoLPT
     * Query to insert new order to db
     * @param address
     * @param phone
     * @param guide
     * @param total
     * @param methodPay
     * @param account_id
     * @param ward_id
     * @param userName
     * @param userEmail
     */
    @Modifying
    @Query(value = "CALL create_order(:address, " +
                                        ":phone, " +
                                        ":guide, " +
                                        ":total, " +
                                        ":method_pay, " +
                                        ":account_id," +
                                        ":ward_id," +
                                        ":user_name," +
                                        ":user_email);", nativeQuery = true)
    List<Integer> createOrder(@Param("address") String address,
                              @Param("phone") String phone,
                              @Param("guide") String guide,
                              @Param("total") Double total,
                              @Param("method_pay") Integer methodPay,
                              @Param("account_id") Integer account_id,
                              @Param("ward_id") Integer ward_id,
                              @Param("user_name") String userName,
                              @Param("user_email") String userEmail);

    /**
     * Author : CaoLPT
     * Find order by ID
     * @param orderId
     */
    @Query(value="SELECT * FROM order_bill WHERE order_id =:order_id", nativeQuery = true)
    Order getOrderById(@Param("order_id") Integer orderId);
}
