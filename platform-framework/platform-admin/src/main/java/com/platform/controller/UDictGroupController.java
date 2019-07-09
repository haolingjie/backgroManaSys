package com.platform.controller;

import com.platform.entity.UDictGroupEntity;
import com.platform.entity.UDictOptionEntity;
import com.platform.service.UDictGroupService;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 业务字典表Controller
 *
 * @author lipengjun
 * @date 2019-07-01 15:17:01
 */
@Controller
@RequestMapping("udictgroup")
public class UDictGroupController {
    @Autowired
    private UDictGroupService uDictGroupService;
    @Autowired
    private UDictOptionService uDictOptionService;
    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("udictgroup:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<UDictGroupEntity> uDictGroupList = uDictGroupService.queryList(query);
        int total = uDictGroupService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(uDictGroupList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("udictgroup:info")
    @ResponseBody
    public R info(@PathVariable("id") String id) {
        UDictGroupEntity uDictGroup = uDictGroupService.queryObject(id);

        return R.ok().put("uDictGroup", uDictGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("udictgroup:save")
    @ResponseBody
    public R save(@RequestBody UDictGroupEntity uDictGroup) {
        uDictGroupService.save(uDictGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("udictgroup:update")
    @ResponseBody
    public R update(@RequestBody UDictGroupEntity uDictGroup) {
        uDictGroupService.update(uDictGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("udictgroup:delete")
    @ResponseBody
    public R delete(@RequestBody String[] ids) {
        uDictGroupService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<UDictGroupEntity> list = uDictGroupService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/queryAllCodeById/{id}")
    @ResponseBody
    public R queryAllCodeById(@PathVariable("id") String id) {
        UDictGroupEntity uDictGroup = uDictGroupService.queryObject(id);
        String groupcode = uDictGroup.getGroupcode();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("groupcode",groupcode);
        List<UDictGroupEntity> list = uDictGroupService.queryList(params);
        if(list != null && list.size()>0){
            Iterator<UDictGroupEntity> iterator = list.iterator();
            while(iterator.hasNext()) {
                UDictGroupEntity entity = iterator.next();
                params = new HashMap<String, Object>();
                params.put("groupCodeId", entity.getId());
                List<UDictOptionEntity> uDictOptionEntities = uDictOptionService.queryList(params);
                if(uDictOptionEntities != null && uDictOptionEntities.size()>0){
                    for (UDictOptionEntity element:
                            uDictOptionEntities) {
                        if("tongCard".equals(element.getOptioncode())){
                            iterator.remove();
                            continue;
                        }
                    }
                }
            }
        }
        return R.ok().put("list", list);
    }
}
