package com.platform.service.impl;

import com.platform.dao.ApiMCardmessageDao;
import com.platform.entity.MCardmessageEntity;
import com.platform.service.ApiMCardmessageService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 消息提示表Service实现类
 *
 * @author lipengjun
 * @date 2019-06-13 11:48:48
 */
@Service("apiMCardmessageService")
@Transactional
public class ApiMCardmessageServiceImpl implements ApiMCardmessageService {
    @Autowired
    private ApiMCardmessageDao apiMCardmessageDao;

    @Override
    public MCardmessageEntity queryObject(String id) {
        return apiMCardmessageDao.queryObject(id);
    }

    @Override
    public List<MCardmessageEntity> queryList(Map<String, Object> map) {
        return apiMCardmessageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return apiMCardmessageDao.queryTotal(map);
    }

    @Override
    public int save(MCardmessageEntity mCardmessage) {
        mCardmessage.setId(IdUtil.createIdbyUUID());
        return apiMCardmessageDao.save(mCardmessage);
    }

    @Override
    public int update(MCardmessageEntity mCardmessage) {
        return apiMCardmessageDao.update(mCardmessage);
    }

    @Override
    public int delete(String id) {
        return apiMCardmessageDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return apiMCardmessageDao.deleteBatch(ids);
    }
}
