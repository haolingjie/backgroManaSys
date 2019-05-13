package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.platform.annotation.IgnoreAuth;
import com.platform.util.HttpClientUtil;
import com.platform.util.wechat.medicalAppointment.AccessToken;
import com.platform.util.wechat.medicalAppointment.WeiXinUtil;
import com.platform.util.wechat.template.WeixinUtil;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


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
    @GetMapping("notToken")
    public R notToken() throws Exception{
        //公众号发送模板消息
        AccessToken accessToken = WeiXinUtil.getAccessToken();
        String url = WeixinUtil.SEND_CUSTOM_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        HashMap<String, Object> map = new HashMap<>();
        map.put("touser","oiTss5Kgo7DDdHfLwtJyeKKTZ6v8");
        map.put("msgtype","text");
        HashMap<String, Object> contentMap  = new HashMap<>();
        contentMap.put("content","111");
        map.put("text",contentMap);
        String s = net.sf.json.JSONObject.fromObject(map).toString();
        System.out.println(s);
        String s1 = HttpClientUtil.doPostJson(url, s);
        System.out.println(s1);
        return R.ok().put("s1", s1);
    }
}
