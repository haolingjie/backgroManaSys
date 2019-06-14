package com.platform.service;

import com.platform.entity.MCardmessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息提示表Service接口
 *
 * @author lipengjun
 * @date 2019-06-13 11:48:48
 */
public interface ApiMCardmessageService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    MCardmessageEntity queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<MCardmessageEntity> queryList(Map<String, Object> map);

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
     * @param mCardmessage 实体
     * @return 保存条数
     */
    int save(MCardmessageEntity mCardmessage);

    /**
     * 根据主键更新实体
     *
     * @param mCardmessage 实体
     * @return 更新条数
     */
    int update(MCardmessageEntity mCardmessage);

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
}
