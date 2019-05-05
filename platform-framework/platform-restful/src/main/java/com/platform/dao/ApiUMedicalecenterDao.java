package com.platform.dao;

import com.platform.entity.UMedicalecenterEntity;

import java.util.List;

/**
 * 套餐明细表Dao
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public interface ApiUMedicalecenterDao extends BaseDao<UMedicalecenterEntity> {

    public List<UMedicalecenterEntity> queryCityAddress ();

    public List<UMedicalecenterEntity> queryAreaAddress ();
}
