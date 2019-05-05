package com.platform.controller;

import com.platform.entity.UDataRuleEntity;
import com.platform.service.UDataRuleService;
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
 * 公共数据规则表Controller
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Controller
@RequestMapping("udatarule")
public class UDataRuleController {
    @Autowired
    private UDataRuleService uDataRuleService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("udatarule:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UDataRuleEntity> uDataRuleList = uDataRuleService.queryList(query);
        int total = uDataRuleService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(uDataRuleList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("udatarule:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        UDataRuleEntity uDataRule = uDataRuleService.queryObject(id);

        return R.ok().put("uDataRule", uDataRule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("udatarule:save")
    @ResponseBody
    public R save(@RequestBody UDataRuleEntity uDataRule) {
        uDataRuleService.save(uDataRule);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("udatarule:update")
    @ResponseBody
    public R update(@RequestBody UDataRuleEntity uDataRule) {
        uDataRuleService.update(uDataRule);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("udatarule:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        uDataRuleService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UDataRuleEntity> list = uDataRuleService.queryList(params);

        return R.ok().put("list", list);
    }
}
