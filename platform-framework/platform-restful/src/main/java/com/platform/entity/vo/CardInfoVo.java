package com.platform.entity.vo;

import java.io.Serializable;

public class CardInfoVo implements Serializable {


    /**
     * 卡号
     */
    private String cardcode;
    /**
     * 密码
     */
    private String username;
    /**
     * 身份证号
     */
    private String identitycard;
    /**
     * 性别
     */
    private String sex;
    /**
     * 婚姻状态 0未婚 1已婚
     */
    private String maritalstatus;
    /**
     * 寄送地址
     */
    private String sendaddress;
    /**
     * 手机号
     */
    private String phobenumber;
    /**
     * 体检中心地址
     */
    private String medicalcode;

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getSendaddress() {
        return sendaddress;
    }

    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress;
    }

    public String getPhobenumber() {
        return phobenumber;
    }

    public void setPhobenumber(String phobenumber) {
        this.phobenumber = phobenumber;
    }

    public String getMedicalcode() {
        return medicalcode;
    }

    public void setMedicalcode(String medicalcode) {
        this.medicalcode = medicalcode;
    }
}
