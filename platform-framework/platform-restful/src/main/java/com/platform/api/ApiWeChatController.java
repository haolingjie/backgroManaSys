package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.entity.vo.CardInfoVo;
import com.platform.entity.WXLoginVO;
import com.platform.entity.vo.MedicalCenterVO;
import com.platform.model.page.BusiReservationCardPage;
import com.platform.service.ApiCardService;
import com.platform.service.ApiUMedicalecenterService;
import com.platform.util.wechat.medicalAppointment.LoginUtil;
import com.platform.util.wechat.medicalAppointment.MedicalAppUtil;
import com.platform.utils.ExceptionUtil;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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
        if(LoginUtil.ordinaryLogin.equals(loginVO.getLoginMethod())){
            String passWord = loginVO.getPassWord();
//        String md5Password = MD5.getMessageDigest(passWord);
            card.setCardcode(loginVO.getCardCode());
            List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(card);
            if(busiReservationCardPages != null && busiReservationCardPages.size()>0){
                for (BusiReservationCardPage cardPage:busiReservationCardPages) {
                    if(StringUtils.equalsIgnoreCase(cardPage.getPassword(),passWord)){
                        List<BusiReservationCardPage> cardList = new ArrayList<BusiReservationCardPage>();
                        cardList.add(cardPage);
                        returnMap.put("cardList",cardList);
                        return R.ok(returnMap);
                    }

                }
                return R.error("密码不正确");
            }else{
                return R.ok("账号不存在");
            }
        }else{
            String identityCard = loginVO.getIdentityCard();
            if(identityCard.substring(16).equalsIgnoreCase(loginVO.getPassWord())){
                BusiReservationCardPage selectCard = new BusiReservationCardPage();
                selectCard.setIdentitycard(loginVO.getIdentityCard());
                List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(selectCard);
                returnMap.put("cardList",busiReservationCardPages);
                return R.ok(returnMap);
            }else{
                return R.error("密码不正确");
            }
        }
    }

    @ApiOperation(value = "微信修改卡片信息", notes = "微信修改卡片信息")
    @IgnoreAuth
    @PostMapping("/editCardInfo")
    public R editCardInfo(@RequestBody CardInfoVo cardInfoVo) {
        log.info("卡片信息接口入参"+ JSONObject.toJSONString(cardInfoVo));
        BusiReservationCardPage cardPage = new BusiReservationCardPage();
        try {
            BeanUtils.copyProperties(cardPage,cardInfoVo);
            cardPage.setOperatetime(new Date());
            apiCardService.update(cardPage);
        }catch (Exception e){
            log.error("微信修改卡片信息异常"+ JSONObject.toJSONString(cardInfoVo)+ ExceptionUtil.getStackTrace(e));
        }
        return R.ok();
    }

    @ApiOperation(value = "选择体检机构地区", notes = "选择体检机构地区")
    @IgnoreAuth
    @PostMapping("/selectOrganArea")
    public R selectOrganArea() {
        Map<String, Object> medicalCenterMap=null;
        try {
            medicalCenterMap = apiUMedicalecenterService.queryCenterAddress();
            log.info("选择体检机构地区结果"+JSONObject.toJSONString(medicalCenterMap));
        }catch (Exception e){
            log.error("选择体检机构地区异常"+ ExceptionUtil.getStackTrace(e));
        }
        return R.ok(medicalCenterMap);
    }

    @ApiOperation(value = "选择体检机构信息", notes = "选择体检机构信息")
    @IgnoreAuth
    @PostMapping("/selectOrgan")
    public R selectOrgan(@RequestBody MedicalCenterVO medicalCenterVO) {
        HashMap<String, Object> returnMap = new HashMap<>();
        log.info("选择体检机构信息入参:"+JSONObject.toJSONString(medicalCenterVO));
        try {
            List<MedicalCenterVO>  medicalCenterVOs= apiUMedicalecenterService.queryCenterInfoByVo(medicalCenterVO);
            returnMap.put("medicalCenterVOs",medicalCenterVOs);
            log.info("选择体检机构信息结果"+JSONObject.toJSONString(medicalCenterVOs));
        }catch (Exception e){
            log.error("选择体检机构信息异常"+ ExceptionUtil.getStackTrace(e));
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
        log.info("初始化选择体检日期页面入参:"+JSONObject.toJSONString(cardInfoVo));
        try {
            card.setCardcode(cardInfoVo.getCardcode());
            List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(card);
            if(busiReservationCardPages != null && busiReservationCardPages.size()>0){
                BeanUtils.copyProperties(cardInfoResult,busiReservationCardPages.get(0));
                cardInfoResult.setMedicalcode(cardInfoVo.getMedicalcode());
            }
            UMedicalecenterEntity centerEntity = apiUMedicalecenterService.queryObject(cardInfoVo.getMedicalcode());
            MedicalCenterVO medicalCenterVO = new MedicalCenterVO();
            BeanUtils.copyProperties(medicalCenterVO,centerEntity);
            cardInfoVo.setMedicalcode(centerEntity.getMedicalecentercode());
            returnMap.put("cardInfoVo",cardInfoResult);
            returnMap.put("medicalCenterVO",medicalCenterVO);
            log.info("初始化体检日期页面查询结果"+JSONObject.toJSONString(returnMap));
        }catch (Exception e){
            log.error("初始化体检日期页面报错"+ ExceptionUtil.getStackTrace(e));
        }
        return R.ok(returnMap);
    }


    @ApiOperation(value = "体检信息确认页面", notes = "体检信息确认页面")
    @IgnoreAuth
    @PostMapping("/reservationSubmit")
    public R reservationSubmit(@RequestBody CardInfoVo cardInfoVo) {
        BusiReservationCardPage cardPage = new BusiReservationCardPage();
        log.info("体检信息确认页面入参:"+JSONObject.toJSONString(cardInfoVo));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cardPage.setMedicaldate(sdf.parse(cardInfoVo.getMedicaldateStr()));
            BeanUtils.copyProperties(cardPage,cardInfoVo);
            cardPage.setOperatetime(new Date());
            //2已预购
            cardPage.setCardstatus(MedicalAppUtil.CARDSTATUS_2);
            apiCardService.update(cardPage);
        }catch (Exception e){
            log.error("体检信息确认页面报错"+ ExceptionUtil.getStackTrace(e));
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
        log.info("初始化预约信息查看页面入参:"+JSONObject.toJSONString(cardInfoVo));
        try {
            card.setCardcode(cardInfoVo.getCardcode());
            List<BusiReservationCardPage> busiReservationCardPages = apiCardService.queryObjectByPage(card);
            if(busiReservationCardPages != null && busiReservationCardPages.size()>0){
                BeanUtils.copyProperties(cardInfoResult,busiReservationCardPages.get(0));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cardInfoResult.setMedicaldateStr(sdf.format(busiReservationCardPages.get(0).getMedicaldate()));
            }
            UMedicalecenterEntity centerEntity = apiUMedicalecenterService.queryObject(cardInfoVo.getMedicalcode());
            cardInfoResult.setMedicalcode(centerEntity.getId());
            MedicalCenterVO medicalCenterVO = new MedicalCenterVO();
            BeanUtils.copyProperties(medicalCenterVO,centerEntity);
            returnMap.put("cardInfoVo",cardInfoResult);
            returnMap.put("medicalCenterVO",medicalCenterVO);
            log.info("初始化预约信息查看页面"+JSONObject.toJSONString(returnMap));
        }catch (Exception e){
            log.error("初始化预约信息查看页面"+ ExceptionUtil.getStackTrace(e));
        }
        return R.ok(returnMap);
    }

}
