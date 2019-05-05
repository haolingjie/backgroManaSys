package com.platform.service;

import com.platform.entity.UDataRuleEntity;

import java.util.List;
import java.util.Map;

/**
 * 公共数据规则表Service接口
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public interface UDataRuleService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    UDataRuleEntity queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UDataRuleEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param uDataRule 实体
     * @return 保存条数
     */
    int save(UDataRuleEntity uDataRule);

    /**
     * 根据主键更新实体
     *
     * @param uDataRule 实体
     * @return 更新条数
     */
    int update(UDataRuleEntity uDataRule);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(String[] ids);


    /**
     *
     * @param entity
     * @return
     */
    List<UDataRuleEntity> queryListByEntity(UDataRuleEntity entity);
}
