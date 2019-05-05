package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 医疗预约用户信息表实体
 * 表名 u_user
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public class UUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 用户id
     */
    private Long usercode;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 性别
     */
    private String sex;
    /**
     * 公司代码
     */
    private String comcode;
    /**
     * 手机号
     */
    private String phobenumber;
    /**
     * 身份证号
     */
    private String identitycard;
    /**
     * 住宿地址
     */
    private String address;
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
     * 设置：用户id
     */
    public void setUsercode(Long usercode) {
        this.usercode = usercode;
    }

    /**
     * 获取：用户id
     */
    public Long getUsercode() {
        return usercode;
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
     * 设置：住宿地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：住宿地址
     */
    public String getAddress() {
        return address;
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
