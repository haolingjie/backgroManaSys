package com.platform.controller;

import com.platform.entity.UCompanyEntity;
import com.platform.service.UCompanyService;
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
 * 公司信息表Controller
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:30
 */
@Controller
@RequestMapping("ucompany")
public class UCompanyController {
    @Autowired
    private UCompanyService uCompanyService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ucompany:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UCompanyEntity> uCompanyList = uCompanyService.queryList(query);
        int total = uCompanyService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(uCompanyList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ucompany:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        UCompanyEntity uCompany = uCompanyService.queryObject(id);

        return R.ok().put("uCompany", uCompany);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ucompany:save")
    @ResponseBody
    public R save(@RequestBody UCompanyEntity uCompany) {
        uCompanyService.save(uCompany);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ucompany:update")
    @ResponseBody
    public R update(@RequestBody UCompanyEntity uCompany) {
        uCompanyService.update(uCompany);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ucompany:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        uCompanyService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UCompanyEntity> list = uCompanyService.queryList(params);

        return R.ok().put("list", list);
    }
}
