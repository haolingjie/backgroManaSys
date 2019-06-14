package com.platform.entity;

import com.platform.utils.IdUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息提示表实体
 * 表名 m_cardmessage
 *
 * @author lipengjun
 * @date 2019-06-13 11:48:48
 */
public class MCardmessageEntity implements Serializable {
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
     * 医疗卡状态 0：未激活，1：已激活，2已预购，3已到检，4：已过期
     */
    private String cardstatus;
    /**
     * 消息有效位：0无效，1有效
     */
    private String status;
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
    }    /**
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
     * 设置：消息有效位：0无效，1有效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：消息有效位：0无效，1有效
     */
    public String getStatus() {
        return status;
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
