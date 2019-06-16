package com.platform.service;

import com.platform.entity.UMedicalecenterEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 套餐明细表Service接口
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public interface UMedicalecenterService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    UMedicalecenterEntity queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<UMedicalecenterEntity> queryList(Map<String, Object> map);

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
     * @param uMedicalecenter 实体
     * @return 保存条数
     */
    int save(UMedicalecenterEntity uMedicalecenter);

    /**
     * 根据主键更新实体
     *
     * @param uMedicalecenter 实体
     * @return 更新条数
     */
    int update(UMedicalecenterEntity uMedicalecenter);

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

    void saveList(ArrayList<UMedicalecenterEntity> centerEntities);

    List<UMedicalecenterEntity> queryListNotPage(Map<String, Object> paramMap);
}
