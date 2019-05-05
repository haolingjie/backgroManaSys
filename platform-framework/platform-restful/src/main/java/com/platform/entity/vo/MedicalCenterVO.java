package com.platform.entity.vo;

import java.io.Serializable;

public class MedicalCenterVO implements Serializable {

    private String id;

    /**
     * 城市代码
     */
    private String citycode;
    /**
     * 城市名称
     */
    private String cityname;
    /**
     * 地区代码
     */
    private String areacode;
    /**
     * 地区名称
     */
    private String areaname;
    /**
     * 体检中心名称
     */
    private String medicalecentername;
    /**
     * 体检中心地址
     */
    private String medicalecenteraddress;

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicalecentername() {
        return medicalecentername;
    }

    public void setMedicalecentername(String medicalecentername) {
        this.medicalecentername = medicalecentername;
    }

    public String getMedicalecenteraddress() {
        return medicalecenteraddress;
    }

    public void setMedicalecenteraddress(String medicalecenteraddress) {
        this.medicalecenteraddress = medicalecenteraddress;
    }
}
