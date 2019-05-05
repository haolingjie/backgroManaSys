package com.platform.service.impl;

import com.platform.dao.UCompanyDao;
import com.platform.entity.UCompanyEntity;
import com.platform.service.UCompanyService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 公司信息表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:30
 */
@Service("uCompanyService")
public class UCompanyServiceImpl implements UCompanyService {
    @Autowired
    private UCompanyDao uCompanyDao;

    @Override
    public UCompanyEntity queryObject(String id) {
        return uCompanyDao.queryObject(id);
    }

    @Override
    public List<UCompanyEntity> queryList(Map<String, Object> map) {
        return uCompanyDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uCompanyDao.queryTotal(map);
    }

    @Override
    public int save(UCompanyEntity uCompany) {
        uCompany.setId(IdUtil.createIdbyUUID());
        return uCompanyDao.save(uCompany);
    }

    @Override
    public int update(UCompanyEntity uCompany) {
        return uCompanyDao.update(uCompany);
    }

    @Override
    public int delete(String id) {
        return uCompanyDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uCompanyDao.deleteBatch(ids);
    }
}
