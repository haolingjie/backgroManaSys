package com.platform.dao;

import com.platform.entity.UMedicalecenterEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 套餐明细表Dao
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public interface UMedicalecenterDao extends BaseDao<UMedicalecenterEntity> {

    void saveList(ArrayList<UMedicalecenterEntity> centerEntities);

    List<UMedicalecenterEntity> queryListNotPage(Map<String, Object> paramMap);
}
