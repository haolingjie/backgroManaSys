package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.platform.entity.BReservationcardEntity;
import com.platform.entity.UDataRuleEntity;
import com.platform.entity.UDictGroupEntity;
import com.platform.page.BReservationCardPage;
import com.platform.service.BReservationcardService;
import com.platform.service.UDataRuleService;
import com.platform.service.WeiChatSendMessageService;
import com.platform.util.wechat.template.WeixinUtil;
import com.platform.utils.*;
import com.platform.utils.excel.ExcelExport;
import com.platform.utils.excel.ExcelImport;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    private com.platform.service.WeiChatSendMessageService weiChatSendMessageService;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static ArrayList<BReservationcardEntity> bReservationcardEntities = new ArrayList<>();
    private static ArrayList<BReservationCardPage> BReservationCardPageList = new ArrayList<>();
    private static ArrayList<BReservationcardEntity> sendMessageEntities = new ArrayList<>();

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("breservationcard:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
//        params.put("daterange0",null);
//        params.put("daterange1",null);
        if(params.get("daterange[0]") != null){
            Object date = params.get("daterange[0]");
            Date daterange0=new Date(date.toString());
            params.put("daterange0",daterange0);
        }
        if(params.get("daterange[1]") != null){
            Object date = params.get("daterange[1]");
            Date daterange1=new Date(date.toString());
            params.put("daterange1",daterange1);
        }
        //查询列表数据
        Query query = new Query(params);

        List<BReservationcardEntity> bReservationcardList = bReservationcardService.queryList(query);
//        if(bReservationcardList != null && bReservationcardList.size()>0){
//            for (BReservationcardEntity :entity
//            bReservationcardList) {
//
//            }
//        }
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


    @RequestMapping("/uploadlist")
    @ResponseBody
    public R uploadlist() {
        ArrayList<BReservationcardEntity> cardEntities = new ArrayList<>();
        cardEntities.addAll(bReservationcardEntities);
        bReservationcardEntities.clear();
        PageUtils pageUtil = new PageUtils(cardEntities, cardEntities.size(), cardEntities.size(), 1);
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file) {
        List<String[]> excelData = ExcelImport.getExcelData(file);
//        ArrayList<BReservationcardEntity> bReservationcardEntities = new ArrayList<>();
        bReservationcardEntities.clear();
        if(excelData != null && excelData.size()>1){
            excelData.remove(0);
            for(String[] data :excelData){
                if(data != null && data.length>0){
                    BReservationcardEntity bReservationcardEntity = new BReservationcardEntity();
                    if(data.length>0) {
                        if(StringUtils.isBlank(data[0]) ||  StringUtils.isBlank(data[0].trim())){
                            continue;
                        }
                        UDataRuleEntity entity = new UDataRuleEntity();
                        entity.setRulecode("cardCode");
                        entity.setRuleoption(StringUtils.isBlank(data[0]) ? "" : data[0].trim());
                        List<UDataRuleEntity> uDataRuleEntities = utilDataRuleService.queryListByEntity(entity);
                        if(uDataRuleEntities != null && uDataRuleEntities.size()>0){
                            UDataRuleEntity uDataRuleEntity = uDataRuleEntities.get(0);
                            Long datamax = uDataRuleEntity.getDatamax();
                            datamax++;
                            bReservationcardEntity.setCardcode(entity.getRuleoption()+datamax);
//                            bReservationcardEntity.setPassword(PassWordCreateUtil.createPassWord(8));
                            uDataRuleEntity.setDatamax(datamax);
                            utilDataRuleService.update(uDataRuleEntity);
                        }else{
                            UDataRuleEntity uDataRuleEntity=new UDataRuleEntity();
                            uDataRuleEntity.setRuleoption(entity.getRuleoption());
                            uDataRuleEntity.setRulecode("cardCode");
                            uDataRuleEntity.setRulename("医疗卡账号");
                            uDataRuleEntity.setDatamax(10000000L);
                            bReservationcardEntity.setCardcode(entity.getRuleoption()+uDataRuleEntity.getDatamax());
                            utilDataRuleService.save(uDataRuleEntity);
                        }
                    }
                    if(data.length>1) {
                        bReservationcardEntity.setUsername(StringUtils.isBlank(data[1]) ? "" : data[1].trim());
                    }
                    if(data.length>2) {
                        bReservationcardEntity.setSex(StringUtils.isBlank(data[2]) ? "" : data[2].trim());
                    }
                    if(data.length>3) {
                        bReservationcardEntity.setIdentitycard(StringUtils.isBlank(data[3]) ? "" : data[3].trim());
                    }
                    if(data.length>4) {
                        bReservationcardEntity.setPhobenumber(StringUtils.isBlank(data[4]) ? "" : data[4].trim());
                    }
                    if(data.length>5) {
                        bReservationcardEntity.setSendaddress(StringUtils.isBlank(data[5]) ? "" : data[5].trim());
                    }
                    bReservationcardEntities.add(bReservationcardEntity);
                }
            }
        }
//        bReservationcardService.saveList(bReservationcardEntities);
//       PageUtils pageUtil = new PageUtils(bReservationcardEntities, bReservationcardEntities.size(), bReservationcardEntities.size(), 1);
        return R.ok();
    }

    @RequestMapping("/saveCardInfo")
    @ResponseBody
    public R saveCardInfo(@RequestBody Map<String, Object> params) {
        List<Map<String,Object>> cardInfo = (List)params.get("cardInfo");
        String modifyFlag = params.get("modifyFlag").toString();
        ArrayList<BReservationcardEntity> cardEntities = new ArrayList<>();
        if(cardInfo != null && cardInfo.size()>0) {
            for (Map<String, Object> map : cardInfo) {
                BReservationcardEntity entity = JSON.parseObject(JSON.toJSONString(map), BReservationcardEntity.class);
                entity.setModifyFlag(modifyFlag);
                String sex = StringUtils.equals(entity.getSex(), "男") ? "1" : (StringUtils.equals(entity.getSex(), "女") ? "0" : "");
                entity.setSex(sex);
                entity.setPassword(PassWordCreateUtil.createPassWord(8));
                entity.setCardstatus("1");
                entity.setStartDate(new Date());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, 1);
                entity.setEndDate(calendar.getTime());
                cardEntities.add(entity);
            }
            if (cardEntities != null && cardEntities.size() == 1) {
                bReservationcardService.save(cardEntities.get(0));
            } else if (cardEntities != null && cardEntities.size() > 1) {
                bReservationcardService.saveList(cardEntities);
            }
        }

        return R.ok();
    }


    @RequestMapping("/uploadWeiXinSendMessage")
    @ResponseBody
    public R uploadWeiXinSendMessage(@RequestParam("file") MultipartFile file) {
        List<String[]> excelData = ExcelImport.getExcelData(file);
//        ArrayList<BReservationcardEntity> bReservationcardEntities = new ArrayList<>();
        sendMessageEntities.clear();
        if(excelData != null && excelData.size()>1){
            excelData.remove(0);
            for(String[] data :excelData){
                if(data != null && data.length>0){
                    BReservationcardEntity bReservationcardEntity = new BReservationcardEntity();
                    if(data.length>0) {
                        bReservationcardEntity.setCardcode(data[0]);
                    }

                    sendMessageEntities.add(bReservationcardEntity);
                }
            }
        }
//        bReservationcardService.saveList(bReservationcardEntities);
//       PageUtils pageUtil = new PageUtils(bReservationcardEntities, bReservationcardEntities.size(), bReservationcardEntities.size(), 1);
        return R.ok();
    }

    @RequestMapping("/uploadWeiXinSendMessageList")
    @ResponseBody
    public R uploadWeiXinSendMessageList() {
        ArrayList<BReservationcardEntity> cardEntities = new ArrayList<>();
        cardEntities.addAll(sendMessageEntities);
        sendMessageEntities.clear();
        PageUtils pageUtil = new PageUtils(cardEntities, cardEntities.size(), cardEntities.size(), 1);
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/sendWeiXinMessage")
    @ResponseBody
    public R sendWeiXinMessage(@RequestBody Map<String, Object> params) {
        List<Map<String,Object>> cardInfo = (List)params.get("cardInfo");
        String messageFlag = params.get("messageFlag") == null ? "" : params.get("messageFlag").toString() ;
        String modifyFlag = params.get("messageFlag").toString();
        if(StringUtils.equals(messageFlag,"0")){
            modifyFlag=WeixinUtil.SUCCESS_NOTICE;
        }else if(StringUtils.equals(messageFlag,"1")){
            modifyFlag=WeixinUtil.FAIL_NOTICE;
        }
        ArrayList<BReservationcardEntity> cardEntities = new ArrayList<>();
        if(cardInfo != null && cardInfo.size()>0){
            for (Map<String,Object> map:cardInfo) {
                BReservationcardEntity entity = JSON.parseObject(JSON.toJSONString(map), BReservationcardEntity.class);
                String cardcode = entity.getCardcode();
                List<BReservationcardEntity> bReservationcardList = bReservationcardService.queryByEntity(entity);
                if(bReservationcardList != null && bReservationcardList.size()>0){
                    log.info("微信公众号发送消息开始"+JSON.toJSONString(bReservationcardList.get(0)));
                    if(StringUtils.equals(bReservationcardList.get(0).getCardstatus(),"2")) {
                        weiChatSendMessageService.sendWeiChatMessage(bReservationcardList.get(0), modifyFlag);
                    }
                }
            }
        }
        return R.ok();
    }

    @RequestMapping("/downData")
    @ResponseBody
    public R downData(@RequestBody Map<String, Object> params, HttpServletResponse response) {
        try{
            List<Map<String, Object>> cardInfo = (List) params.get("cardInfo");
            if (cardInfo != null && cardInfo.size() > 0) {
                for (Map<String, Object> map : cardInfo) {
                    BReservationCardPage entity = JSON.parseObject(JSON.toJSONString(map), BReservationCardPage.class);
                    BReservationCardPageList.add(entity);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return R.ok();
    }

    @RequestMapping("/downExcel")
    @ResponseBody
    public R downExcel(HttpServletResponse response) {
        String now = DateUtils.format(new Date(),"yyyyMMdd");
        ExcelExport ee1 = new ExcelExport(now+"-预约卡信息");
        try{
            List<Object[]> list1 = new ArrayList<Object[]>();
            for (BReservationCardPage page:
            BReservationCardPageList) {
                List<Object> obj = new ArrayList<Object>();
                obj.add(page.getCardcode());
                obj.add(page.getPassword());
                obj.add(page.getUsername());
                obj.add(page.getSex());
                obj.add(page.getIdentitycard());
                obj.add(page.getPhobenumber());
                obj.add(page.getMedicalcode());
                obj.add(page.getMedicaldate());
                obj.add(page.getCardstatus());
                obj.add(page.getSendaddress());
                obj.add(page.getModifyFlag());
                obj.add(page.getSetMeal());
                obj.add(page.getStartDate());
                obj.add(page.getEndDate());
                obj.add(page.getInserttime());
                obj.add(page.getOperatetime());
                list1.add(obj.toArray());

            }
            String[] header = new String[]{"卡号","密码","姓名","性别","身份证号","手机号","体检机构","体检日期","医疗卡状态","寄送地址","信息编辑","预约套餐","有效日期起期","有效日期止期","插入时间","更新时间"};
            ee1.addSheetByArray("预约卡信息", list1, header);
            ee1.export(response);
        }catch (Exception e){
            e.printStackTrace();
        }
        BReservationCardPageList.clear();
        return R.ok();
    }

}
