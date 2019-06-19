package com.platform.util.wechat.template;

import com.platform.util.HttpClientUtil;
import com.platform.util.wechat.medicalAppointment.AccessToken;
import com.platform.util.wechat.medicalAppointment.WeiXinUtil;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //发送模板消息的接口
//    public static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    public static final String SEND_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN";


    public static final String SEND_CUSTOM_URL = " https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

//    /**
//     * 获取access_token
//     *
//     * @param appid     凭证
//     * @param appsecret 密钥
//     * @return
//     */
//    public static AccessToken getAccessToken(String appid, String appsecret) {
//        AccessToken accessToken = null;
//        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
//        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
//        // 如果请求成功
//        if (null != jsonObject) {
//            try {
//                accessToken = new AccessToken();
//                accessToken.setToken(jsonObject.getString("access_token"));
//                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
//            } catch (JSONException e) {
//                accessToken = null;
//                // 获取token失败
//                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
//            }
//        }
//        return accessToken;
//    }

    /**
     * 发送模板消息前获取token
     *
     * @param template_id_short 模板库中模板的编号
     * @param t
     * @param m
     */
    public static void sendMessageBefore(String template_id_short, WxTemplate t, Map<String, TemplateData> m)throws Exception {
        AccessToken token = null;
        token = WeiXinUtil.getAccessToken();
        t.setData(m);
        int result = WeixinUtil.sendMessage(t, token.getAccess_token());
    }

    /**
     * 发送模板消息
     *
     * @param t
     * @param accessToken
     * @return
     */
    public static int sendMessage(WxTemplate t, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = SEND_TEMPLATE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(t).toString();
        // 调用接口创建菜单
        String s = doPostJson(url, jsonMenu);
        System.out.println(s);
        return result;
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            URL url3 = new URL(url);
            URI uri = new URI(url3.getProtocol(), url3.getHost(), url3.getPath(), url3.getQuery(), null);
            HttpPost httpPost = new HttpPost(uri);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }


    public static void main(String[] args) {
//        WxTemplate template = new WxTemplate();
////        template.setUrl(""+TimedTask.websiteAndProject+"/weixinTwo/gotoOrderConfirm?orderId="+map.get("orderId"));
//        template.setTouser("oiTss5Kgo7DDdHfLwtJyeKKTZ6v8");
//        template.setTopcolor("#000000");
//        template.setTemplate_id("RzLjLQEakObCwt_aT-SqDKSEp3HwZAJWzx8UlxqHQVY");
//        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
//        TemplateData first = new TemplateData();
//        first.setColor("#000000");
//        first.setValue("您好，您有一条待确认订单。");
////        m.put("first", first);
//        TemplateData keyword1 = new TemplateData();
//        keyword1.setColor("#328392");
//        keyword1.setValue("OD0001");
//        m.put("keyword1", keyword1);
//        TemplateData keyword2 = new TemplateData();
//        keyword2.setColor("#328392");
//        keyword2.setValue("预定订单");
//        m.put("keyword2", keyword2);
//        TemplateData keyword3 = new TemplateData();
//        keyword3.setColor("#328392");
//        keyword3.setValue("大龙虾");
//        m.put("keyword3", keyword3);
//        TemplateData remark = new TemplateData();
//        remark.setColor("#929232");
//        remark.setValue("请及时确认订单！");
//        m.put("keyword4", remark);
//        TemplateData remark4 = new TemplateData();
//        remark4.setColor("#929232");
//        remark4.setValue("请及时确认订单！");
//        m.put("keyword5", remark);
//        template.setData(m);
//        try {
//            WeixinUtil.sendMessageBefore("",template, m);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
