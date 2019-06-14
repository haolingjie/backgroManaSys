package com.platform.controller;

import com.platform.entity.BReservationcardEntity;
import com.platform.entity.MCardmessageEntity;
import com.platform.service.BReservationcardService;
import com.platform.service.MCardmessageService;
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
 * 消息提示表Controller
 *
 * @author lipengjun
 * @date 2019-06-13 11:48:48
 */
@Controller
@RequestMapping("mcardmessage")
public class MCardmessageController {
    @Autowired
    private MCardmessageService mCardmessageService;
    @Autowired
    private BReservationcardService bReservationcardService;
    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("mcardmessage:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<MCardmessageEntity> mCardmessageList = mCardmessageService.queryList(query);
        int total = mCardmessageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(mCardmessageList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("mcardmessage:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        MCardmessageEntity mCardmessage = mCardmessageService.queryObject(id);

        return R.ok().put("mCardmessage", mCardmessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("mcardmessage:save")
    @ResponseBody
    public R save(@RequestBody MCardmessageEntity mCardmessage) {
        mCardmessageService.save(mCardmessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("mcardmessage:update")
    @ResponseBody
    public R update(@RequestBody MCardmessageEntity mCardmessage) {
        mCardmessageService.update(mCardmessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("mcardmessage:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        mCardmessageService.deleteBatch(ids);

        return R.ok();
    }

    @RequestMapping("/activite")
    @ResponseBody
    public R activite(@RequestBody List<String> ids) {
        return mCardmessageService.activite(ids);
    }

    @RequestMapping("/delay")
    @ResponseBody
    public R delay(@RequestBody Map<String, Object> params) {

        return mCardmessageService.delay(params);
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<MCardmessageEntity> list = mCardmessageService.queryList(params);

        return R.ok().put("list", list);
    }

}
