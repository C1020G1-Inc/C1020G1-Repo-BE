package com.auction_website.service.province;

import com.auction_website.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService{
    @Autowired
    private ProvinceRepository provinceRepository;
}
