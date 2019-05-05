package com.platform.controller;

import com.platform.entity.UDictOptionEntity;
import com.platform.service.UDictOptionService;
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
 * 业务字典明细表Controller
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Controller
@RequestMapping("udictoption")
public class UDictOptionController {
    @Autowired
    private UDictOptionService uDictOptionService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("udictoption:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UDictOptionEntity> uDictOptionList = uDictOptionService.queryList(query);
        int total = uDictOptionService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(uDictOptionList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("udictoption:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        UDictOptionEntity uDictOption = uDictOptionService.queryObject(id);

        return R.ok().put("uDictOption", uDictOption);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("udictoption:save")
    @ResponseBody
    public R save(@RequestBody UDictOptionEntity uDictOption) {
        uDictOptionService.save(uDictOption);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("udictoption:update")
    @ResponseBody
    public R update(@RequestBody UDictOptionEntity uDictOption) {
        uDictOptionService.update(uDictOption);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("udictoption:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        uDictOptionService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UDictOptionEntity> list = uDictOptionService.queryList(params);

        return R.ok().put("list", list);
    }
}
