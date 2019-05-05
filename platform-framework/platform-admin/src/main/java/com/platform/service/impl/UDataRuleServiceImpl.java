package com.platform.service.impl;

import com.platform.dao.UDataRuleDao;
import com.platform.entity.UDataRuleEntity;
import com.platform.service.UDataRuleService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 公共数据规则表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("uDataRuleService")
public class UDataRuleServiceImpl implements UDataRuleService {
    @Autowired
    private UDataRuleDao uDataRuleDao;

    @Override
    public UDataRuleEntity queryObject(String id) {
        return uDataRuleDao.queryObject(id);
    }

    @Override
    public List<UDataRuleEntity> queryList(Map<String, Object> map) {
        return uDataRuleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uDataRuleDao.queryTotal(map);
    }

    @Override
    public int save(UDataRuleEntity uDataRule) {
        uDataRule.setId(IdUtil.createIdbyUUID());
        return uDataRuleDao.save(uDataRule);
    }

    @Override
    public int update(UDataRuleEntity uDataRule) {
        return uDataRuleDao.update(uDataRule);
    }

    @Override
    public int delete(String id) {
        return uDataRuleDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uDataRuleDao.deleteBatch(ids);
    }

    @Override
    public List<UDataRuleEntity> queryListByEntity(UDataRuleEntity entity) {
        return uDataRuleDao.queryListByEntity(entity);
    }
}
