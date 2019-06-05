package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.cache.J2CacheUtils;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.entity.vo.CardInfoVo;
import com.platform.entity.WXLoginVO;
import com.platform.entity.vo.MedicalCenterVO;
import com.platform.entity.vo.WeiXinDate;
import com.platform.model.page.BusiReservationCardPage;
import com.platform.service.ApiCardService;
import com.platform.service.ApiUMedicalecenterService;
import com.platform.util.DateUtils;
import com.platform.util.HttpClientUtil;
import com.platform.util.wechat.WechatUtil;
import com.platform.util.wechat.medicalAppointment.LoginUtil;
import com.platform.util.wechat.medicalAppointment.MedicalAppUtil;
import com.platform.util.wechat.template.TemplateData;
import com.platform.util.wechat.template.WeixinUtil;
import com.platform.util.wechat.template.WxTemplate;
import com.platform.utils.Constant;
import com.platform.utils.ExceptionUtil;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.IntegerField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "ApiLoginController|微信登陆")
@RestController
@RequestMapping("/api/wechat")
public class ApiWeChatController {

    @Autowired
    private ApiCardService apiCardService;

    @Autowired
    private ApiUMedicalecenterService apiUMedicalecenterService;
    private static Logger log = LoggerFactory.getLogger(ApiWeChatController.class);

    @ApiOperation(value = "微信登陆", notes = "微信登陆")
    @IgnoreAuth
    @PostMapping("/login")
    public R login(@RequestBody WXLoginVO loginVO) {
        HashMap<String, Object> returnMap = new HashMap<>();
        BusiReservationCardPage card = new BusiReservationCardPage();
        if (LoginUtil.ordinaryLogin.equals(loginVO.getLoginMethod())) {
            String passWord = loginVO.getPassWord();
//        String md5Password = MD5.getMessageDigest(passWord);
            card.setCardcode(loginVO.getCardCode());
            List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(card);
            if (busiReservationCardPages != null && busiReservationCardPages.size() > 0) {
                for (BusiReservationCardPage cardPage : busiReservationCardPages) {
                    if (StringUtils.equalsIgnoreCase(cardPage.getPassword(), passWord)) {
                        List<BusiReservationCardPage> cardList = new ArrayList<BusiReservationCardPage>();
                        cardList.add(cardPage);
                        returnMap.put("cardList", cardList);
                        return R.ok(returnMap);
                    }

                }
                return R.error("密码不正确");
            } else {
                return R.ok("账号不存在");
            }
        } else {
            String identityCard = loginVO.getIdentityCard();
            if (identityCard.substring(12).equalsIgnoreCase(loginVO.getPassWord())) {
                BusiReservationCardPage selectCard = new BusiReservationCardPage();
                selectCard.setIdentitycard(loginVO.getIdentityCard());
                List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(selectCard);
                returnMap.put("cardList", busiReservationCardPages);
                return R.ok(returnMap);
            } else {
                return R.error("密码不正确");
            }
        }
    }

    @ApiOperation(value = "微信修改卡片信息", notes = "微信修改卡片信息")
    @IgnoreAuth
    @PostMapping("/editCardInfo")
    public R editCardInfo(@RequestBody CardInfoVo cardInfoVo) {
        log.info("卡片信息接口入参" + JSONObject.toJSONString(cardInfoVo));
        BusiReservationCardPage cardPage = new BusiReservationCardPage();
        try {
            BeanUtils.copyProperties(cardPage, cardInfoVo);
            cardPage.setOperatetime(new Date());
            apiCardService.update(cardPage);
        } catch (Exception e) {
            log.error("微信修改卡片信息异常" + JSONObject.toJSONString(cardInfoVo) + ExceptionUtil.getStackTrace(e));
        }
        return R.ok();
    }

    @ApiOperation(value = "选择体检机构地区", notes = "选择体检机构地区")
    @IgnoreAuth
    @PostMapping("/selectOrganArea")
    public R selectOrganArea() {
        Map<String, Object> medicalCenterMap = null;
        try {
            medicalCenterMap = apiUMedicalecenterService.queryCenterAddress();
            log.info("选择体检机构地区结果" + JSONObject.toJSONString(medicalCenterMap));
        } catch (Exception e) {
            log.error("选择体检机构地区异常" + ExceptionUtil.getStackTrace(e));
        }
        return R.ok(medicalCenterMap);
    }

    @ApiOperation(value = "选择体检机构信息", notes = "选择体检机构信息")
    @IgnoreAuth
    @PostMapping("/selectOrgan")
    public R selectOrgan(@RequestBody MedicalCenterVO medicalCenterVO) {
        HashMap<String, Object> returnMap = new HashMap<>();
        log.info("选择体检机构信息入参:" + JSONObject.toJSONString(medicalCenterVO));
        try {
            List<MedicalCenterVO> medicalCenterVOs = apiUMedicalecenterService.queryCenterInfoByVo(medicalCenterVO);
            returnMap.put("medicalCenterVOs", medicalCenterVOs);
            log.info("选择体检机构信息结果" + JSONObject.toJSONString(medicalCenterVOs));
        } catch (Exception e) {
            log.error("选择体检机构信息异常" + ExceptionUtil.getStackTrace(e));
        }
        return R.ok(returnMap);
    }

    @ApiOperation(value = "初始化选择体检日期页面", notes = "初始化选择体检日期页面")
    @IgnoreAuth
    @PostMapping("/selectOrganDate")
    public R selectOrganDate(@RequestBody CardInfoVo cardInfoVo) {
        HashMap<String, Object> returnMap = new HashMap<>();
        BusiReservationCardPage card = new BusiReservationCardPage();
        CardInfoVo cardInfoResult = new CardInfoVo();
        log.info("初始化选择体检日期页面入参:" + JSONObject.toJSONString(cardInfoVo));
        try {
            card.setCardcode(cardInfoVo.getCardcode());
            List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(card);
            if (busiReservationCardPages != null && busiReservationCardPages.size() > 0) {
                BeanUtils.copyProperties(cardInfoResult, busiReservationCardPages.get(0));
                cardInfoResult.setMedicalcode(cardInfoVo.getMedicalcode());
            }
            UMedicalecenterEntity centerEntity = apiUMedicalecenterService.queryObject(cardInfoVo.getMedicalcode());
            MedicalCenterVO medicalCenterVO = new MedicalCenterVO();
            BeanUtils.copyProperties(medicalCenterVO, centerEntity);
            cardInfoVo.setMedicalcode(centerEntity.getMedicalecentercode());
            List<Integer> curCanDateList = new ArrayList<>();
            List<WeiXinDate> allOrageDates = new ArrayList<WeiXinDate>();
            String currDate = DateUtils.getCurrDate();
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH);
            //日历，日期选择器
            for(int i=0;i<55;i++) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int weekday = calendar.get(Calendar.DAY_OF_WEEK);
                int forMonth = calendar.get(Calendar.MONTH);
                if(month == forMonth){
                    if(weekday == 1){
//                        curRemoveDateList.add(day);
                    }else{
                        curCanDateList.add(day);
                        WeiXinDate weiXinDate = new WeiXinDate(year, forMonth+1, day);
                        allOrageDates.add(weiXinDate);
                    }
                }else{
                    if(weekday == 1){
//                        nextRemoveDateList.add(day);
                    }else{
                        WeiXinDate weiXinDate = new WeiXinDate(year, forMonth+1, day);
                        allOrageDates.add(weiXinDate);
                    }
                }
                calendar.add(Calendar.DATE, 1);
            }
            String startDate = DateUtils.getNextDay(currDate, 1);
            //排除日期,后期从数据库查出来
            String removeDate = DateUtils.getNextDay(currDate, 5);
//            removeDateList.add(removeDate);
            String endDate = DateUtils.getNextDay(currDate,14);
            returnMap.put("cardInfoVo", cardInfoResult);
            returnMap.put("medicalCenterVO", medicalCenterVO);

            returnMap.put("startDate", startDate);
            returnMap.put("endDate", endDate);
            returnMap.put("curCanDateList", curCanDateList);
//            returnMap.put("curRemoveDateList", curRemoveDateList);
            returnMap.put("allOrageDates", allOrageDates);
//            returnMap.put("nextRemoveDateList", nextRemoveDateList);

            log.info("初始化体检日期页面查询结果" + JSONObject.toJSONString(returnMap));
        } catch (Exception e) {
            log.error("初始化体检日期页面报错" + ExceptionUtil.getStackTrace(e));
        }
        return R.ok(returnMap);
    }


    @ApiOperation(value = "体检信息确认页面", notes = "体检信息确认页面")
    @IgnoreAuth
    @PostMapping("/reservationSubmit")
    public R reservationSubmit(@RequestBody CardInfoVo cardInfoVo) {
        BusiReservationCardPage cardPage = new BusiReservationCardPage();
        log.info("体检信息确认页面入参:" + JSONObject.toJSONString(cardInfoVo));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cardPage.setMedicaldate(sdf.parse(cardInfoVo.getMedicaldateStr()));
            BeanUtils.copyProperties(cardPage, cardInfoVo);
            cardPage.setOperatetime(new Date());
            //2已预购
            cardPage.setCardstatus(MedicalAppUtil.CARDSTATUS_2);
            apiCardService.update(cardPage);
        } catch (Exception e) {
            log.error("体检信息确认页面报错" + ExceptionUtil.getStackTrace(e));
        }
        return R.ok();
    }

    @ApiOperation(value = "初始化预约信息查看页面", notes = "初始化预约信息查看页面")
    @IgnoreAuth
    @PostMapping("/browsingCardInfo")
    public R browsingCardInfo(@RequestBody CardInfoVo cardInfoVo) {
        HashMap<String, Object> returnMap = new HashMap<>();
        BusiReservationCardPage card = new BusiReservationCardPage();
        CardInfoVo cardInfoResult = new CardInfoVo();
        J2CacheUtils.put(Constant.FORMIDCACHE + "mina:openid:" + cardInfoVo.getOpenId(), cardInfoVo.getFormIdList());

        log.info("初始化预约信息查看页面入参:" + JSONObject.toJSONString(cardInfoVo));
        try {
            card.setCardcode(cardInfoVo.getCardcode());
            List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(card);
            if (busiReservationCardPages != null && busiReservationCardPages.size() > 0) {
                BeanUtils.copyProperties(cardInfoResult, busiReservationCardPages.get(0));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cardInfoResult.setMedicaldateStr(sdf.format(busiReservationCardPages.get(0).getMedicaldate()));
            }
            if (StringUtils.isBlank(cardInfoVo.getMedicalcode())) {
                cardInfoVo.setMedicalcode(cardInfoResult.getMedicalcode());
            }
            UMedicalecenterEntity centerEntity = apiUMedicalecenterService.queryObject(cardInfoVo.getMedicalcode());
            cardInfoResult.setMedicalcode(centerEntity.getId());
            MedicalCenterVO medicalCenterVO = new MedicalCenterVO();
            BeanUtils.copyProperties(medicalCenterVO, centerEntity);
            returnMap.put("cardInfoVo", cardInfoResult);
            returnMap.put("medicalCenterVO", medicalCenterVO);
            log.info("初始化预约信息查看页面" + JSONObject.toJSONString(returnMap));
        } catch (Exception e) {
            log.error("初始化预约信息查看页面" + ExceptionUtil.getStackTrace(e));
        }
        return R.ok(returnMap);
    }

    @ApiOperation(value = "微信登陆获取openId", notes = "微信登陆获取openId")
    @IgnoreAuth
    @GetMapping("/wxLogin")
    public R wxLogin(String code) {
        Map<String, Object> resultMap = new HashMap<>();
        String result = HttpClientUtil.doGet(WechatUtil.getOpenIdUrl + code);
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            resultMap=jsonObject;
        }
        return R.ok(resultMap);
    }

    @ApiOperation(value = "微信发送预定成功消息", notes = "微信发送预定成功消息")
    @IgnoreAuth
    @GetMapping("/sendTemplateMessage")
    public R sendTemplateMessage(String code) {
        Map<String, Object> resultMap = new HashMap<>();
        WxTemplate template = new WxTemplate();
//        template.setUrl(""+TimedTask.websiteAndProject+"/weixinTwo/gotoOrderConfirm?orderId="+map.get("orderId"));
        template.setTouser("oiTss5Kgo7DDdHfLwtJyeKKTZ6v8");
        template.setForm_id("formId");
        template.setTopcolor("#000000");
        template.setTopcolor("#000000");
        template.setTemplate_id("RzLjLQEakObCwt_aT-SqDKSEp3HwZAJWzx8UlxqHQVY");
        Map<String, TemplateData> m = new HashMap<String,TemplateData>();
        TemplateData first = new TemplateData();
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
        m.put("keyword5", remark);
        template.setData(m);
        try {
            WeixinUtil.sendMessageBefore("",template, m);
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.ok(resultMap);
    }
}