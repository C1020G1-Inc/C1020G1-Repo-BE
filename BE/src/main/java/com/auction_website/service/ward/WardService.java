package com.auction_website.service.ward;

import com.auction_website.model.Ward;

public interface WardService {
    Iterable<Ward> getWardByDistrictId(int district);
}
