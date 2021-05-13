package com.auction_website.service.district;

import com.auction_website.model.District;
import com.auction_website.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService{
    @Autowired
    private DistrictRepository districtRepository;

    /**
     * author: CaoLPT
     * method: get iterables of district by province id
     * @param provinceId
     */
    @Override
    public Iterable<District> getDistrictByProvinceId(int provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);

    }
}
