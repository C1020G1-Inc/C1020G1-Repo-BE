package com.auction_website.service.province;

import com.auction_website.model.Province;
import com.auction_website.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService{
    @Autowired
    private ProvinceRepository provinceRepository;

    /**
     * author: CaoLPT
     * method: return iterables of all province in database
     */
    @Override
    public Iterable<Province> getAllProvince() {
        return provinceRepository.findAllProvince();
    }
}
