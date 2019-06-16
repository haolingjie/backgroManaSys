package com.platform.service.impl;

import com.platform.dao.UMedicalecenterDao;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.service.UMedicalecenterService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 套餐明细表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("uMedicalecenterService")
public class UMedicalecenterServiceImpl implements UMedicalecenterService {
    @Autowired
    private UMedicalecenterDao uMedicalecenterDao;

    @Override
    public UMedicalecenterEntity queryObject(String id) {
        return uMedicalecenterDao.queryObject(id);
    }

    @Override
    public List<UMedicalecenterEntity> queryList(Map<String, Object> map) {
        return uMedicalecenterDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uMedicalecenterDao.queryTotal(map);
    }

    @Override
    public int save(UMedicalecenterEntity uMedicalecenter) {
        uMedicalecenter.setId(IdUtil.createIdbyUUID());
        return uMedicalecenterDao.save(uMedicalecenter);
    }

    @Override
    public int update(UMedicalecenterEntity uMedicalecenter) {
        return uMedicalecenterDao.update(uMedicalecenter);
    }

    @Override
    public int delete(String id) {
        return uMedicalecenterDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uMedicalecenterDao.deleteBatch(ids);
    }

    @Override
    public void saveList(ArrayList<UMedicalecenterEntity> centerEntities) {
        uMedicalecenterDao.saveList(centerEntities);
    }

    @Override
    public List<UMedicalecenterEntity> queryListNotPage(Map<String, Object> paramMap) {
        return uMedicalecenterDao.queryListNotPage(paramMap);
    }
}
