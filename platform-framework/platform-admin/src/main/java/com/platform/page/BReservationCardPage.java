package com.platform.page;

import java.io.Serializable;
import java.util.Date;

public class BReservationCardPage implements Serializable {
    /**
     * id
     */
//    private String id;
    /**
     * 卡号
     */
    private String cardcode;
    /**
     * 密码
     */
    private String password;
//    /**
//     * 微信openId
//     */
//    private String openId;
//    /**
//     * 公司代码
//     */
//    private String comcode;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 性别
     */
    private String sex;

    /**
     * 婚姻状态 0未婚 1已婚
     */
    private String maritalstatus;
    /**
     * 身份证号
     */
    private String identitycard;
    /**
     * 手机号
     */
    private String phobenumber;
    /**
     * 体检机构
     */
    private String medicalcode;
    /**
     * 体检日期
     */
    private String medicaldate;
    /**
     * 医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期
     */
    private String cardstatus;
    /**
     * 寄送地址
     */
    private String sendaddress;

    /**
     * 信息编辑标识：0,均可编辑，1，用户信息可修改，寄送地址不可修改，2用户信息不可修改，寄送地址可修改，3均不可修改',
     */
    private String modifyFlag;
    /**
     * 套餐类型
     */
    private String setMeal;
    /**
     * 有效期起始日期
     */
    private String startDate;

    /**
     *有效期结束日期
     */
    private String endDate;
    /**
     * 插入时间
     */
    private String inserttime;
    /**
     * 更新时间
     */
    private String operatetime;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
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

    public String getMedicaldate() {
        return medicaldate;
    }

    public void setMedicaldate(String medicaldate) {
        this.medicaldate = medicaldate;
    }

    public String getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(String cardstatus) {
        this.cardstatus = cardstatus;
    }

    public String getSendaddress() {
        return sendaddress;
    }

    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress;
    }

    public String getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public String getSetMeal() {
        return setMeal;
    }

    public void setSetMeal(String setMeal) {
        this.setMeal = setMeal;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getInserttime() {
        return inserttime;
    }

    public void setInserttime(String inserttime) {
        this.inserttime = inserttime;
    }

    public String getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime;
    }
}
