package com.auction_website.controller;

import com.auction_website.model.District;
import com.auction_website.model.Province;
import com.auction_website.model.Ward;
import com.auction_website.service.district.DistrictService;
import com.auction_website.service.province.ProvinceService;
import com.auction_website.service.ward.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    /**
     * author: CaoLPT
     * method: get all provinces in database and attach an JSON in HTTP response
     */
    @GetMapping("/province")
    public ResponseEntity<Iterable<Province>> listProvinces() {
        Iterable<Province> provinces = provinceService.getAllProvince();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    /**
     * author: CaoLPT
     * method: get all districts in database by a province id as JSON in HTTP response
     * @param provinceId
     */
    @GetMapping("/district/{id}")
    public ResponseEntity<Iterable<District>> listDistrictByProvinceId(@PathVariable("id") int provinceId) {
        Iterable<District> districts = districtService.getDistrictByProvinceId(provinceId);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    /**
     * author: CaoLPT
     * method: get all wards in database by a district id as JSON in HTTP response
     * @param districtId
     */
    @GetMapping("/ward/{id}")
    public ResponseEntity<Iterable<Ward>> listWardByDistrictId(@PathVariable("id") int districtId) {
        Iterable<Ward> wards = wardService.getWardByDistrictId(districtId);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }
}
