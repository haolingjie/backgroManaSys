package com.platform.service.impl;

import com.platform.dao.UUserDao;
import com.platform.entity.UUserEntity;
import com.platform.service.UUserService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 医疗预约用户信息表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("uUserService")
public class UUserServiceImpl implements UUserService {
    @Autowired
    private UUserDao uUserDao;

    @Override
    public UUserEntity queryObject(String id) {
        return uUserDao.queryObject(id);
    }

    @Override
    public List<UUserEntity> queryList(Map<String, Object> map) {
        return uUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uUserDao.queryTotal(map);
    }

    @Override
    public int save(UUserEntity uUser) {
        uUser.setId(IdUtil.createIdbyUUID());
        return uUserDao.save(uUser);
    }

    @Override
    public int update(UUserEntity uUser) {
        return uUserDao.update(uUser);
    }

    @Override
    public int delete(String id) {
        return uUserDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uUserDao.deleteBatch(ids);
    }
}
