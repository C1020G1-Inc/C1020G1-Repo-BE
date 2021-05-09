package com.auction_website.model;

import javax.persistence.*;

@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_name")
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "province_id")
    private Province province;

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer id) {
        this.districtId = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String name) {
        this.districtName = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
