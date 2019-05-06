package com.platform.entity;

import com.platform.utils.IdUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约卡信息表实体
 * 表名 b_reservationcard
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public class BReservationcardEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 卡号
     */
    private String cardcode;
    /**
     * 密码
     */
    private String password;
    /**
     * 公司代码
     */
    private String comcode;
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
    private Date medicaldate;
    /**
     * 医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期
     */
    private String cardstatus;
    /**
     * 寄送地址
     */
    private String sendaddress;

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
        if(id == null){
            id= IdUtil.createIdbyUUID();
        }
        return id;
    }
    /**
     * 设置：卡号
     */
    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    /**
     * 获取：卡号
     */
    public String getCardcode() {
        return cardcode;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：公司代码
     */
    public void setComcode(String comcode) {
        this.comcode = comcode;
    }

    /**
     * 获取：公司代码
     */
    public String getComcode() {
        return comcode;
    }
    /**
     * 设置：用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名称
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置：性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取：性别
     */
    public String getSex() {
        return sex;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    /**
     * 设置：身份证号
     */
    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    /**
     * 获取：身份证号
     */
    public String getIdentitycard() {
        return identitycard;
    }
    /**
     * 设置：手机号
     */
    public void setPhobenumber(String phobenumber) {
        this.phobenumber = phobenumber;
    }

    /**
     * 获取：手机号
     */
    public String getPhobenumber() {
        return phobenumber;
    }
    /**
     * 设置：体检机构
     */
    public void setMedicalcode(String medicalcode) {
        this.medicalcode = medicalcode;
    }

    /**
     * 获取：体检机构
     */
    public String getMedicalcode() {
        return medicalcode;
    }
    /**
     * 设置：体检日期
     */
    public void setMedicaldate(Date medicaldate) {
        this.medicaldate = medicaldate;
    }

    /**
     * 获取：体检日期
     */
    public Date getMedicaldate() {
        return medicaldate;
    }
    /**
     * 设置：医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期
     */
    public void setCardstatus(String cardstatus) {
        this.cardstatus = cardstatus;
    }

    /**
     * 获取：医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期
     */
    public String getCardstatus() {
        return cardstatus;
    }
    /**
     * 设置：寄送地址
     */
    public void setSendaddress(String sendaddress) {
        this.sendaddress = sendaddress;
    }

    /**
     * 获取：寄送地址
     */
    public String getSendaddress() {
        return sendaddress;
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
        if(inserttime == null){
            inserttime=new Date();
        }
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
        operatetime=new Date();
        return operatetime;
    }
}
