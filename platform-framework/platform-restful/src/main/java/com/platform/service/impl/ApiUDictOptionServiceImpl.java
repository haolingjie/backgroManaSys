package com.platform.service.impl;

import com.platform.dao.ApiUDictOptionDao;
import com.platform.entity.UDictOptionEntity;
import com.platform.service.ApiUDictOptionService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 业务字典明细表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("apiUDictOptionService")
@Transactional
public class ApiUDictOptionServiceImpl implements ApiUDictOptionService {
    @Autowired
    private ApiUDictOptionDao apiUDictOptionDao;

    @Override
    public UDictOptionEntity queryObject(String id) {
        return apiUDictOptionDao.queryObject(id);
    }

    @Override
    public List<UDictOptionEntity> queryList(Map<String, Object> map) {
        return apiUDictOptionDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return apiUDictOptionDao.queryTotal(map);
    }

    @Override
    public int save(UDictOptionEntity uDictOption) {
        uDictOption.setId(IdUtil.createIdbyUUID());
        return apiUDictOptionDao.save(uDictOption);
    }

    @Override
    public int update(UDictOptionEntity uDictOption) {
        return apiUDictOptionDao.update(uDictOption);
    }

    @Override
    public int delete(String id) {
        return apiUDictOptionDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return apiUDictOptionDao.deleteBatch(ids);
    }
}
