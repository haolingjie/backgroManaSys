package com.platform.entity.vo;

import java.io.Serializable;

public class WeiXinDate implements Serializable {

    public WeiXinDate() {
    }

    public WeiXinDate(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private Integer year;
    private Integer month;
    private Integer day;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
