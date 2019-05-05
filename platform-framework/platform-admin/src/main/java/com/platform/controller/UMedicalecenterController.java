package com.platform.controller;

import com.platform.entity.UMedicalecenterEntity;
import com.platform.service.UMedicalecenterService;
import com.platform.utils.PageUtils;
import com.platform.utils.Query;
import com.platform.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
