package com.platform.service.impl;

import com.platform.dao.UDictOptionDao;
import com.platform.entity.UDictOptionEntity;
import com.platform.service.UDictOptionService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 业务字典明细表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("uDictOptionService")
public class UDictOptionServiceImpl implements UDictOptionService {
    @Autowired
    private UDictOptionDao uDictOptionDao;

    @Override
    public UDictOptionEntity queryObject(String id) {
        return uDictOptionDao.queryObject(id);
    }

    @Override
    public List<UDictOptionEntity> queryList(Map<String, Object> map) {
        return uDictOptionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uDictOptionDao.queryTotal(map);
    }

    @Override
    public int save(UDictOptionEntity uDictOption) {
        uDictOption.setId(IdUtil.createIdbyUUID());
        return uDictOptionDao.save(uDictOption);
    }

    @Override
    public int update(UDictOptionEntity uDictOption) {
        return uDictOptionDao.update(uDictOption);
    }

    @Override
    public int delete(String id) {
        return uDictOptionDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uDictOptionDao.deleteBatch(ids);
    }
}
