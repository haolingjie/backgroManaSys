package com.platform.controller;

import com.platform.entity.BReservationcardEntity;
import com.platform.entity.UDataRuleEntity;
import com.platform.entity.UDictGroupEntity;
import com.platform.service.BReservationcardService;
import com.platform.service.UDataRuleService;
import com.platform.utils.PageUtils;
import com.platform.utils.PassWordCreateUtil;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 预约卡信息表Controller
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Controller
@RequestMapping("breservationcard")
public class BReservationcardController {
    @Autowired
    private BReservationcardService bReservationcardService;
    @Autowired
    private UDataRuleService utilDataRuleService;
    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("breservationcard:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BReservationcardEntity> bReservationcardList = bReservationcardService.queryList(query);
        int total = bReservationcardService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(bReservationcardList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("breservationcard:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        BReservationcardEntity bReservationcard = bReservationcardService.queryObject(id);

        return R.ok().put("bReservationcard", bReservationcard);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("breservationcard:save")
    @ResponseBody
    public R save(@RequestBody BReservationcardEntity bReservationcard) {
        bReservationcardService.save(bReservationcard);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("breservationcard:update")
    @ResponseBody
    public R update(@RequestBody BReservationcardEntity bReservationcard) {
        bReservationcardService.update(bReservationcard);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("breservationcard:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        bReservationcardService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<BReservationcardEntity> list = bReservationcardService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping(value = "/generateCardInfo",method = RequestMethod.POST)
    @ResponseBody
    public R generateCardInfo(UDataRuleEntity entity,int count) {
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
