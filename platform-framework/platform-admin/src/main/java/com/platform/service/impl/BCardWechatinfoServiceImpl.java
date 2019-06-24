package com.platform.service.impl;

import com.platform.dao.BCardWechatinfoDao;
import com.platform.entity.BCardWechatinfoEntity;
import com.platform.service.BCardWechatinfoService;
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
@Service("bCardWechatinfoService")
public class BCardWechatinfoServiceImpl implements BCardWechatinfoService {
    @Autowired
    private BCardWechatinfoDao bCardWechatinfoDao;

    @Override
    public BCardWechatinfoEntity queryObject(String id) {
        return bCardWechatinfoDao.queryObject(id);
    }

    @Override
    public List<BCardWechatinfoEntity> queryList(Map<String, Object> map) {
        return bCardWechatinfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return bCardWechatinfoDao.queryTotal(map);
    }

    @Override
    public int save(BCardWechatinfoEntity bCardWechatinfo) {
        bCardWechatinfo.setId(IdUtil.createIdbyUUID());
        return bCardWechatinfoDao.save(bCardWechatinfo);
    }

    @Override
    public int update(BCardWechatinfoEntity bCardWechatinfo) {
        return bCardWechatinfoDao.update(bCardWechatinfo);
    }

    @Override
    public int delete(String id) {
        return bCardWechatinfoDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return bCardWechatinfoDao.deleteBatch(ids);
    }
}
