package com.platform.entity;

import com.platform.utils.IdUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务字典明细表实体
 * 表名 u_dict_option
 *
 * @author lipengjun
 * @date 2019-07-01 15:17:01
 */
public class UDictOptionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 明细代码
     */
    private String optioncode;
    /**
     * 明细名称
     */
    private String optionname;
    /**
     * 组id
     */
    private String groupCodeId;
    /**
     * 明细主要说明
     */
    private String optionimport;
    /**
     * 明细描述
     */
    private String optiondescribe;
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
     * 设置：明细代码
     */
    public void setOptioncode(String optioncode) {
        this.optioncode = optioncode;
    }

    /**
     * 获取：明细代码
     */
    public String getOptioncode() {
        return optioncode;
    }
    /**
     * 设置：明细名称
     */
    public void setOptionname(String optionname) {
        this.optionname = optionname;
    }

    /**
     * 获取：明细名称
     */
    public String getOptionname() {
        return optionname;
    }

    public String getGroupCodeId() {
        return groupCodeId;
    }

    public void setGroupCodeId(String groupCodeId) {
        this.groupCodeId = groupCodeId;
    }

    /**
     * 设置：明细主要说明
     */
    public void setOptionimport(String optionimport) {
        this.optionimport = optionimport;
    }

    /**
     * 获取：明细主要说明
     */
    public String getOptionimport() {
        return optionimport;
    }
    /**
     * 设置：明细描述
     */
    public void setOptiondescribe(String optiondescribe) {
        this.optiondescribe = optiondescribe;
    }

    /**
     * 获取：明细描述
     */
    public String getOptiondescribe() {
        return optiondescribe;
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
