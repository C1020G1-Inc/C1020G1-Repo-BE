package com.auction_website.repository;

import com.auction_website.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Query(value = "SELECT * FROM province", nativeQuery = true)
    Iterable<Province> findAllProvince();
}
