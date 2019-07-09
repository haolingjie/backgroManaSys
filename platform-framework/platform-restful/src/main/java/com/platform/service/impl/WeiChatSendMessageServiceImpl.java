package com.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.entity.BCardWechatinfoEntity;
import com.platform.entity.BReservationcardEntity;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.service.ApiBCardWechatinfoService;
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
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WeiChatSendMessageServiceImpl implements WeiChatSendMessageService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiUMedicalecenterService apiUMedicalecenterService;
    @Autowired
    private ApiBCardWechatinfoService apiBCardWechatinfoService;
    @Override
    public void sendWeiChatMessage(BReservationcardEntity entity, String sendMessageFlag){
        String medicalcode = entity.getMedicalcode();
        UMedicalecenterEntity centerEntity = apiUMedicalecenterService.queryObject(medicalcode);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cardcode",entity.getCardcode());
//        paramMap.put("openid",entity.getOpenId());
        List<BCardWechatinfoEntity> bCardWechatinfoEntities = apiBCardWechatinfoService.queryList(paramMap);
        WxTemplate template = new WxTemplate();
        Map<String, TemplateData> m = new HashMap<String, TemplateData>();
        template.setTouser(entity.getOpenId());
        if(bCardWechatinfoEntities != null && bCardWechatinfoEntities.size()>0){
            template.setForm_id(bCardWechatinfoEntities.get(0).getFormid());
        }
        if(StringUtils.equals(sendMessageFlag, WeixinUtil.SUCCESS_NOTICE)){
//        template.setUrl(""+TimedTask.websiteAndProject+"/weixinTwo/gotoOrderConfirm?orderId="+map.get("orderId"));
            template.setTemplate_id(WeixinUtil.SUCCESS_TEMPLATE_ID);
//            TemplateData first = new TemplateData();
//            first.setValue("尊敬的"+entity.getUsername() +"先生/女士：您好！ ");
//            m.put("first", first);
            //体检时间
            TemplateData keyword1 = new TemplateData();
            keyword1.setValue(entity.getUsername());
            m.put("keyword1", keyword1);
            TemplateData keyword2 = new TemplateData();
            keyword2.setValue(DateUtils.dateToString(entity.getMedicaldate()));
            m.put("keyword2", keyword2);
            TemplateData keyword3 = new TemplateData();
            TemplateData keyword4 = new TemplateData();
            //体检医院 地点
            if(centerEntity != null) {
                keyword3.setValue(centerEntity.getMedicalecentername());
                keyword4.setValue(centerEntity.getMedicalecenteraddress());
            }else{
                keyword3.setValue("");
                keyword4.setValue("");
            }
            m.put("keyword3", keyword3);
            m.put("keyword4", keyword4);
//            TemplateData keyword5 = new TemplateData();
            //其他事项
//            keyword5.setValue("A、体检前一天晚上早休息，勿饮酒；体检当天完成体检前勿进食。 B、请务必携带身份证原件");
//            m.put("keyword5", keyword5);
//            TemplateData keyword5 = new TemplateData();
//            keyword5.setValue("88888888");
//            m.put("keyword5", keyword5);
//            TemplateData keyword6 = new TemplateData();
//            keyword6.setValue("感谢您对拓哉大健康的关注，祝您体检愉快");
//            m.put("keyword6", keyword6);
            template.setData(m);

        }else if(StringUtils.equals(sendMessageFlag, WeixinUtil.FAIL_NOTICE)){
            template.setTemplate_id(WeixinUtil.FAIL_TEMPLATE_ID);
            TemplateData keyword1 = new TemplateData();
            keyword1.setValue(entity.getUsername());
            m.put("keyword1", keyword1);
            TemplateData keyword2 = new TemplateData();
            keyword2.setValue(DateUtils.dateToString(entity.getMedicaldate()));
            m.put("keyword2", keyword2);
            TemplateData keyword3 = new TemplateData();
            TemplateData keyword4 = new TemplateData();
            //体检医院 地点
            if(centerEntity != null) {
                keyword3.setValue("预约失败");
//                keyword4.setValue(centerEntity.getMedicalecenteraddress());
            }else{
                keyword3.setValue("预约失败");
//                keyword4.setValue("");
            }
            m.put("keyword3", keyword3);
//            m.put("keyword4", keyword4);
            template.setData(m);
        }
        log.info("模板消息发送"+JSON.toJSONString(template));
        try {
            String s = WeixinUtil.sendMessageBefore("", template, m);
            if(bCardWechatinfoEntities != null && bCardWechatinfoEntities.size()>0){
                JSONObject jsonObject = JSON.parseObject(s);
                String errcode= jsonObject.get("errcode") == null ? "" : jsonObject.get("errcode").toString();
                if(errcode.equals("0")){
                    bCardWechatinfoEntities.get(0).setStatus("2");
                }else{
                    bCardWechatinfoEntities.get(0).setStatus("3");
                }
                bCardWechatinfoEntities.get(0).setErrorMessage(s);
                apiBCardWechatinfoService.update(bCardWechatinfoEntities.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
