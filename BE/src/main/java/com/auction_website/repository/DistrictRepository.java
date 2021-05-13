package com.auction_website.repository;

import com.auction_website.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    /**
     * Author : CaoLPT
     * Query get list districts by province_id from db
     * @return
     */
    @Query(value = "SELECT * FROM district WHERE district.province_id = :provinceId", nativeQuery = true)
    Iterable<District> findAllByProvinceId(int provinceId);
}
