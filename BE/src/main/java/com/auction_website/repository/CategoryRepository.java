package com.auction_website.repository;

import com.auction_website.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    /**
     * author: ThinhTHB
     * method: get category
     * */
    @Query(value = "select * from category",nativeQuery = true)
    Iterable<Category> getCategory();

}
