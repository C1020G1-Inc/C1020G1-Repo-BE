package com.auction_website.repository;

import com.auction_website.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select * from `category`",nativeQuery = true)
    List<Category> findAllCategory();

    /**
     * author: ThinhTHB
     * method: get category
     * */
    @Query(value = "select * from category",nativeQuery = true)
    List<Category> getCategory();

}
