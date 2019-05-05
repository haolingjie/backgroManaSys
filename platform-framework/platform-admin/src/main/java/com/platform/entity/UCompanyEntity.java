package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司信息表实体
 * 表名 u_company
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:30
 */
public class UCompanyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 公司代码
     */
    private String comcode;
    /**
     * 公司名称
     */
    private String comname;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 手机号
     */
    private String phobenumber;
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
     * 设置：公司名称
     */
    public void setComname(String comname) {
        this.comname = comname;
    }

    /**
     * 获取：公司名称
     */
    public String getComname() {
        return comname;
    }
    /**
     * 设置：公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：公司地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：联系人
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * 获取：联系人
     */
    public String getLinkman() {
        return linkman;
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
