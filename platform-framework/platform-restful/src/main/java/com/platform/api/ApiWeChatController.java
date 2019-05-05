package com.platform.api;

import com.alibaba.fastjson.JSONObject;
import com.platform.annotation.IgnoreAuth;
import com.platform.entity.vo.CardInfoVo;
import com.platform.entity.WXLoginVO;
import com.platform.model.page.BusiReservationCardPage;
import com.platform.service.ApiCardService;
import com.platform.service.ApiUMedicalecenterService;
import com.platform.util.wechat.medicalAppointment.LoginUtil;
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

    @ApiOperation(value = "选择体检机构信息", notes = "选择体检机构信息")
    @IgnoreAuth
    @PostMapping("/selectOrgan")
    public R selectOrgan() {
        Map<String, Object> medicalCenterMap=null;
        try {
            medicalCenterMap = apiUMedicalecenterService.queryCenterAddress();
            log.info("选择体检机构信息结果"+JSONObject.toJSONString(medicalCenterMap));
        }catch (Exception e){
            log.error("选择体检机构信息异常"+ ExceptionUtil.getStackTrace(e));
        }
        return R.ok(medicalCenterMap);
    }
}
