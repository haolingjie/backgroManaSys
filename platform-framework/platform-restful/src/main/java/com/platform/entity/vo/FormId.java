package com.platform.entity.vo;

import java.io.Serializable;

public class FormId implements Serializable {

    private String formId;

    private String expire;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
}
