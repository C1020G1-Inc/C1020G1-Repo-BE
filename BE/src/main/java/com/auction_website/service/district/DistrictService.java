package com.auction_website.service.district;

import com.auction_website.model.District;

public interface DistrictService {
    Iterable<District> getDistrictByProvinceId(int provinceId);
}
