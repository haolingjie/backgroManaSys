package com.platform.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务字典表实体
 * 表名 u_dict_group
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public class UDictGroupEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 组代码
     */
    private String groupcode;
    /**
     * 组名称
     */
    private String groupname;
    /**
     * 类型代码
     */
    private String categorycode;
    /**
     * 类型名称
     */
    private String categoryname;
    /**
     * 有效值
     */
    private String validstatus;
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
     * 设置：组代码
     */
    public void setGroupcode(String groupcode) {
        this.groupcode = groupcode;
    }

    /**
     * 获取：组代码
     */
    public String getGroupcode() {
        return groupcode;
    }
    /**
     * 设置：组名称
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * 获取：组名称
     */
    public String getGroupname() {
        return groupname;
    }
    /**
     * 设置：类型代码
     */
    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    /**
     * 获取：类型代码
     */
    public String getCategorycode() {
        return categorycode;
    }
    /**
     * 设置：类型名称
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    /**
     * 获取：类型名称
     */
    public String getCategoryname() {
        return categoryname;
    }
    /**
     * 设置：有效值
     */
    public void setValidstatus(String validstatus) {
        this.validstatus = validstatus;
    }

    /**
     * 获取：有效值
     */
    public String getValidstatus() {
        return validstatus;
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
