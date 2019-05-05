package com.platform.service.impl;

import com.platform.dao.UDictGroupDao;
import com.platform.entity.UDictGroupEntity;
import com.platform.service.UDictGroupService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 业务字典表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("uDictGroupService")
public class UDictGroupServiceImpl implements UDictGroupService {
    @Autowired
    private UDictGroupDao uDictGroupDao;

    @Override
    public UDictGroupEntity queryObject(String id) {
        return uDictGroupDao.queryObject(id);
    }

    @Override
    public List<UDictGroupEntity> queryList(Map<String, Object> map) {
        return uDictGroupDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uDictGroupDao.queryTotal(map);
    }

    @Override
    public int save(UDictGroupEntity uDictGroup) {
        uDictGroup.setId(IdUtil.createIdbyUUID());
        return uDictGroupDao.save(uDictGroup);
    }

    @Override
    public int update(UDictGroupEntity uDictGroup) {
        return uDictGroupDao.update(uDictGroup);
    }

    @Override
    public int delete(String id) {
        return uDictGroupDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uDictGroupDao.deleteBatch(ids);
    }
}
