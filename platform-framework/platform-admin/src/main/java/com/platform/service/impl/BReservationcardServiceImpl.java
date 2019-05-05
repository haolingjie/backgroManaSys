package com.platform.service.impl;

import com.platform.dao.BReservationcardDao;
import com.platform.entity.BReservationcardEntity;
import com.platform.service.BReservationcardService;
import com.platform.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 预约卡信息表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("bReservationcardService")
@Transactional(rollbackFor = Exception.class)
public class BReservationcardServiceImpl implements BReservationcardService {
    @Autowired
    private BReservationcardDao bReservationcardDao;

    @Override
    public BReservationcardEntity queryObject(String id) {
        return bReservationcardDao.queryObject(id);
    }

    @Override
    public List<BReservationcardEntity> queryList(Map<String, Object> map) {
        return bReservationcardDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return bReservationcardDao.queryTotal(map);
    }

    @Override
    public int save(BReservationcardEntity bReservationcard) {
        bReservationcard.setId(IdUtil.createIdbyUUID());
        return bReservationcardDao.save(bReservationcard);
    }

    @Override
    public int update(BReservationcardEntity bReservationcard) {
        return bReservationcardDao.update(bReservationcard);
    }

    @Override
    public int delete(String id) {
        return bReservationcardDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return bReservationcardDao.deleteBatch(ids);
    }

    @Override
    public void saveList(List<BReservationcardEntity> cardList) {
        bReservationcardDao.saveList(cardList);
    }
}
