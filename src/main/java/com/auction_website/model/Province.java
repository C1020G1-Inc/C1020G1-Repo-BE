package com.auction_website.model;

import javax.persistence.*;

@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name")
    private String provinceName;

    public Province() {
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer id) {
        this.provinceId = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String name) {
        this.provinceName = name;
    }
}
