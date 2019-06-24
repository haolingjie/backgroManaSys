package com.platform.service.impl;

import com.platform.dao.ApiBCardWechatinfoDao;
import com.platform.entity.BCardWechatinfoEntity;
import com.platform.service.ApiBCardWechatinfoService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 微信用户信息表Service实现类
 *
 * @author lipengjun
 * @date 2019-06-23 21:17:12
 */
@Service("apiBCardWechatinfoService")
public class ApiBCardWechatinfoServiceImpl implements ApiBCardWechatinfoService {
    @Autowired
    private ApiBCardWechatinfoDao apiBCardWechatinfoDao;

    @Override
    public BCardWechatinfoEntity queryObject(String id) {
        return apiBCardWechatinfoDao.queryObject(id);
    }

    @Override
    public List<BCardWechatinfoEntity> queryList(Map<String, Object> map) {
        return apiBCardWechatinfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return apiBCardWechatinfoDao.queryTotal(map);
    }

    @Override
    public int save(BCardWechatinfoEntity bCardWechatinfo) {
        bCardWechatinfo.setId(IdUtil.createIdbyUUID());
        return apiBCardWechatinfoDao.save(bCardWechatinfo);
    }

    @Override
    public int update(BCardWechatinfoEntity bCardWechatinfo) {
        return apiBCardWechatinfoDao.update(bCardWechatinfo);
    }

    @Override
    public int delete(String id) {
        return apiBCardWechatinfoDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return apiBCardWechatinfoDao.deleteBatch(ids);
    }
}
