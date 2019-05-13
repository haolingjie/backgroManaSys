package com.platform.util.wechat.template;

import com.platform.util.wechat.medicalAppointment.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时获取微信access_token的线程
 */
public class TokenThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    // 第三方用户唯一凭证
    public static String appid = "xxx";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "xxxx";
    public static AccessToken accessToken = null;//保存ACCESS_TOKEN到内存

    public void run() {
//        while (true) {
//            try {
//                accessToken = WeixinUtil.getAccessToken(appid, appsecret);
//                if(null != accessToken) {
//                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpires_in(), accessToken.getAccess_token());
//                    // 休眠7000秒
//                    Thread.sleep((accessToken.getExpires_in() - 200) * 1000);
//                }else{
//                    // 如果access_token为null，60秒后再获取
//                    Thread.sleep(60 * 1000);
//                }
//            } catch (InterruptedException e) {
//                try{
//                    Thread.sleep(60 * 1000);
//                } catch (InterruptedException e1) {
//                    log.error("{}", e1);
//                }
//                log.error("{}", e);
//            }
//        }
    }
}
