package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.platform.entity.BReservationcardEntity;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.service.ApiUMedicalecenterService;
import com.platform.service.WeiChatSendMessageService;
import com.platform.util.DateUtils;
import com.platform.util.wechat.template.TemplateData;
import com.platform.util.wechat.template.WeixinUtil;
import com.platform.util.wechat.template.WxTemplate;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class WeiChatSendMessageServiceImpl implements WeiChatSendMessageService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiUMedicalecenterService apiUMedicalecenterService;

    @Override
    public void sendWeiChatMessage(BReservationcardEntity entity, String sendMessageFlag){
        String medicalcode = entity.getMedicalcode();
        UMedicalecenterEntity centerEntity = apiUMedicalecenterService.queryObject(medicalcode);

        if(StringUtils.equals(sendMessageFlag, WeixinUtil.SUCCESS_NOTICE)){
            Map<String, Object> resultMap = new HashMap<>();
            WxTemplate template = new WxTemplate();
//        template.setUrl(""+TimedTask.websiteAndProject+"/weixinTwo/gotoOrderConfirm?orderId="+map.get("orderId"));
            template.setTouser(entity.getOpenId());
//            template.setForm_id("f15850e88c334fdb97710b8db0724049");
            template.setTemplate_id(WeixinUtil.SUCCESS_TEMPLATE_ID);
            Map<String, TemplateData> m = new HashMap<String, TemplateData>();
            TemplateData first = new TemplateData();
            first.setValue("尊敬的"+entity.getUsername() +"先生/女士：您好！ ");
            m.put("first", first);
            //体检时间
            TemplateData keyword1 = new TemplateData();
            keyword1.setValue(DateUtils.dateToString(entity.getMedicaldate()));
            m.put("keyword1", keyword1);
            TemplateData keyword2 = new TemplateData();
            TemplateData keyword3 = new TemplateData();
            //体检医院 地点
            if(centerEntity != null) {
                keyword2.setValue(centerEntity.getMedicalecentername());
                keyword3.setValue(centerEntity.getMedicalecenteraddress());
            }else{
                keyword2.setValue("");
                keyword3.setValue("");
            }
            m.put("keyword2", keyword2);
            m.put("keyword3", keyword3);
            TemplateData keyword4 = new TemplateData();
            //其他事项
            keyword4.setValue("A、体检前一天晚上早休息，勿饮酒；体检当天完成体检前勿进食。 B、请务必携带身份证原件");
            m.put("keyword4", keyword4);
            TemplateData keyword5 = new TemplateData();
            keyword5.setValue("88888888");
            m.put("keyword5", keyword5);
            TemplateData remark = new TemplateData();
            remark.setValue("感谢您对拓哉大健康的关注，祝您体检愉快");
            m.put("remark", remark);
            template.setData(m);
            log.info("模板消息发送"+JSON.toJSONString(template));
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
