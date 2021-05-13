package com.auction_website.repository;

import com.auction_website.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

    /**
     * Author : CaoLPT
     * Query get list wards by district_id from db
     * @return
     */
    @Query(value = "SELECT * FROM ward WHERE ward.district_id = :districtId", nativeQuery = true)
    Iterable<Ward> findAllByDistrictId(int districtId);
}
