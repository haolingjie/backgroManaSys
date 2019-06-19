package com.platform.service.impl;

import com.platform.dao.ApiUDictGroupDao;
import com.platform.entity.UDictGroupEntity;
import com.platform.service.ApiUDictGroupService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 业务字典表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("apiUDictGroupService")
@Transactional
public class ApiUDictGroupServiceImpl implements ApiUDictGroupService {
    @Autowired
    private ApiUDictGroupDao apiUDictGroupDao;

    @Override
    public UDictGroupEntity queryObject(String id) {
        return apiUDictGroupDao.queryObject(id);
    }

    @Override
    public List<UDictGroupEntity> queryList(Map<String, Object> map) {
        return apiUDictGroupDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return apiUDictGroupDao.queryTotal(map);
    }

    @Override
    public int save(UDictGroupEntity uDictGroup) {
        uDictGroup.setId(IdUtil.createIdbyUUID());
        return apiUDictGroupDao.save(uDictGroup);
    }

    @Override
    public int update(UDictGroupEntity uDictGroup) {
        return apiUDictGroupDao.update(uDictGroup);
    }

    @Override
    public int delete(String id) {
        return apiUDictGroupDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return apiUDictGroupDao.deleteBatch(ids);
    }
}
