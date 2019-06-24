package com.platform.controller;

import com.platform.entity.BCardWechatinfoEntity;
import com.platform.service.BCardWechatinfoService;
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
 * 微信用户信息表Controller
 *
 * @author lipengjun
 * @date 2019-06-23 21:17:12
 */
@Controller
@RequestMapping("bcardwechatinfo")
public class BCardWechatinfoController {
    @Autowired
    private BCardWechatinfoService bCardWechatinfoService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("bcardwechatinfo:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BCardWechatinfoEntity> bCardWechatinfoList = bCardWechatinfoService.queryList(query);
        int total = bCardWechatinfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(bCardWechatinfoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("bcardwechatinfo:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        BCardWechatinfoEntity bCardWechatinfo = bCardWechatinfoService.queryObject(id);

        return R.ok().put("bCardWechatinfo", bCardWechatinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("bcardwechatinfo:save")
    @ResponseBody
    public R save(@RequestBody BCardWechatinfoEntity bCardWechatinfo) {
        bCardWechatinfoService.save(bCardWechatinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("bcardwechatinfo:update")
    @ResponseBody
    public R update(@RequestBody BCardWechatinfoEntity bCardWechatinfo) {
        bCardWechatinfoService.update(bCardWechatinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("bcardwechatinfo:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        bCardWechatinfoService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<BCardWechatinfoEntity> list = bCardWechatinfoService.queryList(params);

        return R.ok().put("list", list);
    }
}
