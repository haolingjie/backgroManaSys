package com.platform.entity;

import com.platform.utils.IdUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信用户信息表实体
 * 表名 b_card_wechatinfo
 *
 * @author lipengjun
 * @date 2019-06-23 21:17:12
 */
public class BCardWechatinfoEntity implements Serializable {
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
     * 微信openId
     */
    private String openid;
    /**
     * 表单Id
     */
    private String formid;
    /**
     * 7天后的过期时间戳
     */
    private Date expire;
    /**
     * 发送状态：0，无效，1有效，2发送成功，3发送失败
     */
    private String status;

    /**
     * 发送结果
     */
    private String errorMessage;
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
     * 设置：微信openId
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取：微信openId
     */
    public String getOpenid() {
        return openid;
    }
    /**
     * 设置：表单Id
     */
    public void setFormid(String formid) {
        this.formid = formid;
    }

    /**
     * 获取：表单Id
     */
    public String getFormid() {
        return formid;
    }
    /**
     * 设置：7天后的过期时间戳
     */
    public void setExpire(Date expire) {
        this.expire = expire;
    }

    /**
     * 获取：7天后的过期时间戳
     */
    public Date getExpire() {
        return expire;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
