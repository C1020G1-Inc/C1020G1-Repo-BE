package com.auction_website.service.ward;

import com.auction_website.model.Ward;
import com.auction_website.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl implements WardService{
    @Autowired
    private WardRepository wardRepository;

    /**
     * author: CaoLPT
     * method: return iterables of wards by district id
     * @param districtId
     * @return
     */
    @Override
    public Iterable<Ward> getWardByDistrictId(int districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }
}
