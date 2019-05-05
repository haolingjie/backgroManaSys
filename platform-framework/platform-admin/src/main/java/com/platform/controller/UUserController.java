package com.platform.controller;

import com.platform.entity.UUserEntity;
import com.platform.service.UUserService;
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
 * 医疗预约用户信息表Controller
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Controller
@RequestMapping("uuser")
public class UUserController {
    @Autowired
    private UUserService uUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("uuser:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UUserEntity> uUserList = uUserService.queryList(query);
        int total = uUserService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(uUserList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("uuser:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        UUserEntity uUser = uUserService.queryObject(id);

        return R.ok().put("uUser", uUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("uuser:save")
    @ResponseBody
    public R save(@RequestBody UUserEntity uUser) {
        uUserService.save(uUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("uuser:update")
    @ResponseBody
    public R update(@RequestBody UUserEntity uUser) {
        uUserService.update(uUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("uuser:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        uUserService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UUserEntity> list = uUserService.queryList(params);

        return R.ok().put("list", list);
    }
}
