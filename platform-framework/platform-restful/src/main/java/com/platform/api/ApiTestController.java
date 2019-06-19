package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.platform.annotation.IgnoreAuth;
import com.platform.util.HttpClientUtil;
import com.platform.util.wechat.medicalAppointment.AccessToken;
import com.platform.util.wechat.medicalAppointment.WeiXinUtil;
import com.platform.util.wechat.template.TemplateData;
import com.platform.util.wechat.template.WeixinUtil;
import com.platform.util.wechat.template.WxTemplate;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * API测试接口
 *
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
@Api(value = "ApiTestController|一个用来测试Token的控制器")
@RestController
@RequestMapping("/api/test")
public class ApiTestController {

    /**
     * 忽略Token验证测试
     */
    @ApiOperation(value = "忽略Token验证测试", notes = "无需token也能访问")
    @IgnoreAuth
    @GetMapping("/notToken")
    public R notToken() throws Exception{
        //公众号发送模板消息
        AccessToken accessToken = WeiXinUtil.getAccessToken();
        String url = WeixinUtil.SEND_CUSTOM_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        Map<String, Object> resultMap = new HashMap<>();
        WxTemplate template = new WxTemplate();
//        template.setUrl(""+TimedTask.websiteAndProject+"/weixinTwo/gotoOrderConfirm?orderId="+map.get("orderId"));
        template.setTouser("oiTss5Kgo7DDdHfLwtJyeKKTZ6v8");
        template.setForm_id("8f4001b2c99d47d282d5e40e9f6bbeb5");
        template.setTopcolor("#000000");
        template.setTopcolor("#000000");
        template.setTemplate_id("RzLjLQEakObCwt_aT-SqDKSEp3HwZAJWzx8UlxqHQVY");
        Map<String, TemplateData> m = new HashMap<String,TemplateData>();
        /*TemplateData first = new TemplateData();
        first.setColor("#000000");
        first.setValue("您好，您有一条待确认订单。");
//        m.put("first", first);
        TemplateData keyword1 = new TemplateData();
        keyword1.setColor("#328392");
        keyword1.setValue("OD0001");
        m.put("keyword1", keyword1);
        TemplateData keyword2 = new TemplateData();
        keyword2.setColor("#328392");
        keyword2.setValue("预定订单");
        m.put("keyword2", keyword2);
        TemplateData keyword3 = new TemplateData();
        keyword3.setColor("#328392");
        keyword3.setValue("大龙虾");
        m.put("keyword3", keyword3);
        TemplateData remark = new TemplateData();
        remark.setColor("#929232");
        remark.setValue("请及时确认订单！");
        m.put("keyword4", remark);
        TemplateData remark4 = new TemplateData();
        remark4.setColor("#929232");
        remark4.setValue("请及时确认订单！");
        m.put("keyword5", remark);*/
        template.setData(m);
        String s = net.sf.json.JSONObject.fromObject(template).toString();
        System.out.println(s);
        url = WeixinUtil.SEND_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        String s1 = WeixinUtil.doPostJson(url, s);
        System.out.println(s1);
        return R.ok().put("s1", s1);
    }
}
