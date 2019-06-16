package com.platform.controller;

import com.alibaba.fastjson.JSON;
import com.platform.entity.BReservationcardEntity;
import com.platform.entity.UDataRuleEntity;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.service.UMedicalecenterService;
import com.platform.utils.*;
import com.platform.utils.excel.ExcelImport;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 套餐明细表Controller
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Controller
@RequestMapping("umedicalecenter")
public class UMedicalecenterController {
    @Autowired
    private UMedicalecenterService uMedicalecenterService;
    private static ArrayList<UMedicalecenterEntity> uMedicalecenterEntityList = new ArrayList<>();

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("umedicalecenter:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UMedicalecenterEntity> uMedicalecenterList = uMedicalecenterService.queryList(query);
        int total = uMedicalecenterService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(uMedicalecenterList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("umedicalecenter:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        UMedicalecenterEntity uMedicalecenter = uMedicalecenterService.queryObject(id);

        return R.ok().put("uMedicalecenter", uMedicalecenter);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("umedicalecenter:save")
    @ResponseBody
    public R save(@RequestBody UMedicalecenterEntity uMedicalecenter) {
        uMedicalecenterService.save(uMedicalecenter);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("umedicalecenter:update")
    @ResponseBody
    public R update(@RequestBody UMedicalecenterEntity uMedicalecenter) {
        uMedicalecenterService.update(uMedicalecenter);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("umedicalecenter:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        uMedicalecenterService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UMedicalecenterEntity> list = uMedicalecenterService.queryList(params);

        return R.ok().put("list", list);
    }

    @RequestMapping("/uploadlist")
    @ResponseBody
    public R uploadlist() {
        ArrayList<UMedicalecenterEntity> centerEntities = new ArrayList<>();
        centerEntities.addAll(uMedicalecenterEntityList);
        uMedicalecenterEntityList.clear();
        PageUtils pageUtil = new PageUtils(centerEntities, centerEntities.size(), centerEntities.size(), 1);
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile file) {
        List<String[]> excelData = ExcelImport.getExcelData(file);
//        ArrayList<BReservationcardEntity> bReservationcardEntities = new ArrayList<>();
        uMedicalecenterEntityList.clear();
        if(excelData != null && excelData.size()>1){
            excelData.remove(0);
            Map<String, String> paramMap = new HashMap<String, String>();
            for(String[] data :excelData){
                if(data != null && data.length>0){
                    UMedicalecenterEntity uMedicalecenterEntity = new UMedicalecenterEntity();
                    if(data.length>0) {
                        if(StringUtils.isNotBlank(data[0])){
                            paramMap.put("data0",data[0]);
                        }
                        uMedicalecenterEntity.setCityname(StringUtils.isBlank(data[0]) ? paramMap.get("data0") : data[0].trim());
                    }
                    if(data.length>1) {
                        if(StringUtils.isNotBlank(data[1])){
                            paramMap.put("data1",data[1]);
                        }
                        uMedicalecenterEntity.setAreaname(StringUtils.isBlank(data[1]) ? paramMap.get("data1") : data[1].trim());
                    }
                    if(data.length>2) {
                        if(StringUtils.isNotBlank(data[2])){
                            paramMap.put("data2",data[2]);
                        }
                        uMedicalecenterEntity.setMedicalbrandname(StringUtils.isBlank(data[2]) ? paramMap.get("data2") : data[2].trim());
                    }
                    if(data.length>3) {
                        if(StringUtils.isNotBlank(data[3])){
                            paramMap.put("data3",data[3]);
                        }
                        uMedicalecenterEntity.setMedicalecentername(StringUtils.isBlank(data[3]) ? paramMap.get("data3") : data[3].trim());
                    }
                    if(data.length>4) {
                        if(StringUtils.isNotBlank(data[4])){
                            paramMap.put("data4",data[4]);
                        }
                        uMedicalecenterEntity.setMedicalecenteraddress(StringUtils.isBlank(data[4]) ? paramMap.get("data4") : data[4].trim());
                    }
                    if(data.length>5) {
                        uMedicalecenterEntity.setNotopenDay(StringUtils.isBlank(data[5]) ? "" : data[5].trim());
                    }
                    uMedicalecenterEntityList.add(uMedicalecenterEntity);
                }
            }
        }
//        bReservationcardService.saveList(bReservationcardEntities);
//       PageUtils pageUtil = new PageUtils(bReservationcardEntities, bReservationcardEntities.size(), bReservationcardEntities.size(), 1);
        return R.ok();
    }

    @RequestMapping("/saveCenterInfo")
    @ResponseBody
    public R saveCenterInfo(@RequestBody Map<String, Object> params) {
        List<Map<String,Object>> centerInfo = (List)params.get("centerInfo");
        ArrayList<UMedicalecenterEntity> centerEntities = new ArrayList<>();
        if(centerInfo != null && centerInfo.size()>0){
            for (Map<String,Object> map:centerInfo) {
                UMedicalecenterEntity entity = JSON.parseObject(JSON.toJSONString(map), UMedicalecenterEntity.class);
//                entity.setAddressmudifyFlag(addressmudifyFlag);
//                entity.setPassword(PassWordCreateUtil.createPassWord(8));
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("cityname",entity.getCityname());
                List<UMedicalecenterEntity> citynamecenterEntities = uMedicalecenterService.queryListNotPage(paramMap);
                if(citynamecenterEntities != null && citynamecenterEntities.size()>0){
                    //查地区
                    paramMap.put("areaname",entity.getAreaname());
                    List<UMedicalecenterEntity> areanamecenterEntities = uMedicalecenterService.queryListNotPage(paramMap);
                    if(areanamecenterEntities != null && areanamecenterEntities.size()>0){
                        //查品牌
                        paramMap.put("medicalbrandname",entity.getMedicalbrandname());
                        List<UMedicalecenterEntity> medicalbrandnamecenterEntities = uMedicalecenterService.queryListNotPage(paramMap);
                        if(medicalbrandnamecenterEntities != null && medicalbrandnamecenterEntities.size()>0){
                            //查中心名称
                            paramMap.put("medicalecentername",entity.getMedicalecentername());
                            List<UMedicalecenterEntity> medicalecenternamecenterEntities = uMedicalecenterService.queryListNotPage(paramMap);
                            if(medicalecenternamecenterEntities != null && medicalecenternamecenterEntities.size()>0){
                                continue;
                            }else{
                                entity.setCitycode(citynamecenterEntities.get(0).getCitycode());
                                entity.setAreacode(areanamecenterEntities.get(0).getAreacode());
                            }
                        }else{
                            entity.setCitycode(citynamecenterEntities.get(0).getCitycode());
                            entity.setAreacode(areanamecenterEntities.get(0).getAreacode());
                        }
                    }else{
                        entity.setCitycode(citynamecenterEntities.get(0).getCitycode());
                        entity.setAreacode(IdUtil.createIdbyUUID());
                    }
                }else{
                    entity.setCitycode(IdUtil.createIdbyUUID());
                    entity.setAreacode(IdUtil.createIdbyUUID());
                }
                    uMedicalecenterService.save(entity);
            }
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/updateNotAllOpneDay")
    @ResponseBody
    public R updateNotAllOpneDay(@RequestBody Map<String, Object> params) {
        String notopenDay = params.get("notopenDay").toString();
        List<UMedicalecenterEntity> list = uMedicalecenterService.queryList(params);
        if(list != null && list.size()>0){
            for (UMedicalecenterEntity entity:list) {
                if(StringUtils.isNotBlank(entity.getNotopenDay())){
                    entity.setNotopenDay(entity.getNotopenDay()+","+notopenDay);
                }else{
                    entity.setNotopenDay(notopenDay);
                }
                uMedicalecenterService.update(entity);
            }
        }
        return R.ok();
    }
}
