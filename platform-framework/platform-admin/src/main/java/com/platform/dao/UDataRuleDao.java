package com.platform.dao;

import com.platform.entity.UDataRuleEntity;

import java.util.List;

/**
 * 公共数据规则表Dao
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public interface UDataRuleDao extends BaseDao<UDataRuleEntity> {

    List<UDataRuleEntity> queryListByEntity(UDataRuleEntity entity);
}
