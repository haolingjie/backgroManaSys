package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 套餐明细表实体
 * 表名 u_medicalecenter
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public class UMedicalecenterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
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
     * 医疗品牌代码
     */
    private String medicalbrandcode;
    /**
     * 医疗品牌名称
     */
    private String medicalbrandname;
    /**
     * 体检中心代码
     */
    private String medicalecentercode;
    /**
     * 体检中心名称
     */
    private String medicalecentername;
    /**
     * 体检中心地址
     */
    private String medicalecenteraddress;
    /**
     * 不开放日期
     */
    private String notopenDay;
    /**
     * 插入时间
     */
    private Date inserttime;
    /**
     * 更新时间
     */
    private Date operatetime;

    /**
     * 设置：id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取：id
     */
    public String getId() {
        return id;
    }
    /**
     * 设置：城市代码
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    /**
     * 获取：城市代码
     */
    public String getCitycode() {
        return citycode;
    }
    /**
     * 设置：城市名称
     */
    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    /**
     * 获取：城市名称
     */
    public String getCityname() {
        return cityname;
    }
    /**
     * 设置：地区代码
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    /**
     * 获取：地区代码
     */
    public String getAreacode() {
        return areacode;
    }
    /**
     * 设置：地区名称
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    /**
     * 获取：地区名称
     */
    public String getAreaname() {
        return areaname;
    }
    /**
     * 设置：医疗品牌代码
     */
    public void setMedicalbrandcode(String medicalbrandcode) {
        this.medicalbrandcode = medicalbrandcode;
    }

    /**
     * 获取：医疗品牌代码
     */
    public String getMedicalbrandcode() {
        return medicalbrandcode;
    }
    /**
     * 设置：医疗品牌名称
     */
    public void setMedicalbrandname(String medicalbrandname) {
        this.medicalbrandname = medicalbrandname;
    }

    /**
     * 获取：医疗品牌名称
     */
    public String getMedicalbrandname() {
        return medicalbrandname;
    }
    /**
     * 设置：体检中心代码
     */
    public void setMedicalecentercode(String medicalecentercode) {
        this.medicalecentercode = medicalecentercode;
    }

    /**
     * 获取：体检中心代码
     */
    public String getMedicalecentercode() {
        return medicalecentercode;
    }
    /**
     * 设置：体检中心名称
     */
    public void setMedicalecentername(String medicalecentername) {
        this.medicalecentername = medicalecentername;
    }

    /**
     * 获取：体检中心名称
     */
    public String getMedicalecentername() {
        return medicalecentername;
    }
    /**
     * 设置：体检中心地址
     */
    public void setMedicalecenteraddress(String medicalecenteraddress) {
        this.medicalecenteraddress = medicalecenteraddress;
    }

    /**
     * 获取：体检中心地址
     */
    public String getMedicalecenteraddress() {
        return medicalecenteraddress;
    }

    public String getNotopenDay() {
        return notopenDay;
    }

    public void setNotopenDay(String notopenDay) {
        this.notopenDay = notopenDay;
    }

    /**
     * 设置：插入时间
     */
    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    /**
     * 获取：插入时间
     */
    public Date getInserttime() {
        return inserttime;
    }
    /**
     * 设置：更新时间
     */
    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    /**
     * 获取：更新时间
     */
    public Date getOperatetime() {
        return operatetime;
    }
}
