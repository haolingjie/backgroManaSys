package com.platform.service.impl;

import com.platform.entity.BReservationcardEntity;
import com.platform.service.ApiUMedicalecenterService;
import com.platform.service.WeiChatSendMessageService;
import com.platform.util.wechat.template.TemplateData;
import com.platform.util.wechat.template.WeixinUtil;
import com.platform.util.wechat.template.WxTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class WeiChatSendMessageServiceImpl implements WeiChatSendMessageService {

    @Autowired
    private ApiUMedicalecenterService apiUMedicalecenterService;

    @Override
    public void sendWeiChatMessage(BReservationcardEntity entity, String sendMessageFlag){
        if(StringUtils.equals(sendMessageFlag, WeixinUtil.SUCCESS_NOTICE)){
            Map<String, Object> resultMap = new HashMap<>();
            WxTemplate template = new WxTemplate();
//        template.setUrl(""+TimedTask.websiteAndProject+"/weixinTwo/gotoOrderConfirm?orderId="+map.get("orderId"));
            template.setTouser(entity.getOpenId());
//            template.setForm_id("f15850e88c334fdb97710b8db0724049");
            template.setTemplate_id(WeixinUtil.SUCCESS_TEMPLATE_ID);
            Map<String, TemplateData> m = new HashMap<String, TemplateData>();
            TemplateData first = new TemplateData();
            first.setValue("您好，您有一条待确认订单。");
            m.put("first", first);
            TemplateData keyword1 = new TemplateData();
            keyword1.setValue("OD0001");
            m.put("keyword1", keyword1);
            TemplateData keyword2 = new TemplateData();
            keyword2.setValue("预定订单");
            m.put("keyword2", keyword2);
            TemplateData keyword3 = new TemplateData();
            keyword3.setValue("大龙虾");
            m.put("keyword3", keyword3);
            TemplateData remark = new TemplateData();
            remark.setValue("请及时确认订单！");
            m.put("keyword4", remark);
            TemplateData remark4 = new TemplateData();
            remark4.setValue("请及时确认订单！");
            m.put("keyword5", remark);
            template.setData(m);
            try {
                WeixinUtil.sendMessageBefore("", template, m);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(StringUtils.equals(sendMessageFlag, WeixinUtil.FAIL_NOTICE)){

        }else{

        }

    }

}
