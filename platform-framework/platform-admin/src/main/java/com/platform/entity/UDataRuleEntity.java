package com.platform.entity;

import com.platform.utils.IdUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共数据规则表实体
 * 表名 u_data_rule
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public class UDataRuleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 规则code
     */
    private String rulecode;
    /**
     * 规则名称
     */
    private String rulename;
    /**
     * 规则选项
     */
    private String ruleoption;
    /**
     * 目前最大数据
     */
    private Long datamax;
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
     * 设置：规则code
     */
    public void setRulecode(String rulecode) {
        this.rulecode = rulecode;
    }

    /**
     * 获取：规则code
     */
    public String getRulecode() {
        return rulecode;
    }
    /**
     * 设置：规则名称
     */
    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    /**
     * 获取：规则名称
     */
    public String getRulename() {
        return rulename;
    }
    /**
     * 设置：规则选项
     */
    public void setRuleoption(String ruleoption) {
        this.ruleoption = ruleoption;
    }

    /**
     * 获取：规则选项
     */
    public String getRuleoption() {
        return ruleoption;
    }
    /**
     * 设置：目前最大数据
     */
    public void setDatamax(Long datamax) {
        this.datamax = datamax;
    }

    /**
     * 获取：目前最大数据
     */
    public Long getDatamax() {
        return datamax;
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
