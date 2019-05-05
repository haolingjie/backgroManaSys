package com.platform.controller;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.BReservationcardEntity;
import com.platform.entity.UDataRuleEntity;
import com.platform.service.BReservationcardService;
import com.platform.service.UDataRuleService;

import com.platform.utils.PassWordCreateUtil;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "ApiCardController|一个用来生成医疗卡信息的控制器")
@RestController
@RequestMapping("/api/card")
public class ApiCardController {
    @Autowired
    private BReservationcardService bReservationcardService;
    @Autowired
    private UDataRuleService utilDataRuleService;

    @IgnoreAuth
    @RequestMapping(value = "/generateCardInfo",method = RequestMethod.POST)
    @ResponseBody
    public R generateCardInfo(UDataRuleEntity entity, int count) {
        List<UDataRuleEntity> uDataRuleEntities = utilDataRuleService.queryListByEntity(entity);
        if(uDataRuleEntities != null && uDataRuleEntities.size()>0){
            UDataRuleEntity uDataRuleEntity = uDataRuleEntities.get(0);
            Long datamax = uDataRuleEntity.getDatamax();
            List<BReservationcardEntity> cardList=new ArrayList<BReservationcardEntity>();
            for(int i=0;i<count;i++){
                datamax++;
                BReservationcardEntity cardEntity = new BReservationcardEntity();
                cardEntity.setCardcode(entity.getRuleoption()+datamax);
                cardEntity.setPassword(PassWordCreateUtil.createPassWord(8));
                cardList.add(cardEntity);
            }
            bReservationcardService.saveList(cardList);
            uDataRuleEntity.setDatamax(datamax);
            utilDataRuleService.update(uDataRuleEntity);
        }
        return R.ok("医疗卡信息添加成功");
    }
}
