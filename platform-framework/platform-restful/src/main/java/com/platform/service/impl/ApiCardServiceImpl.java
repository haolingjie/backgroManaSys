package com.platform.service.impl;

import com.platform.dao.ApiCardMapper;
import com.platform.model.page.BusiReservationCardPage;
import com.platform.service.ApiCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("apiCardService")
@Transactional
public class ApiCardServiceImpl implements ApiCardService {

    @Autowired
    private ApiCardMapper cardDao;
    @Override
    public BusiReservationCardPage queryObject(String cardCode) {
        return cardDao.queryObject(cardCode);
    }
    @Override
    public List<BusiReservationCardPage> queryList(Map<String, Object> map) {
        return cardDao.queryList(map);
    }
    @Override
    public int queryTotal(Map<String, Object> map) {
        return cardDao.queryTotal(map);
    }
    @Override
    public void save(String mobile, String password) {
//        UserVo user = new UserVo();
//        user.setMobile(mobile);
//        user.setUserName(mobile);
//        user.setPassWord(DigestUtils.sha256Hex(password));
//        cardDao.save(user);
    }
    @Override
    public void save(BusiReservationCardPage userVo) {
        cardDao.save(userVo);
    }
    @Override
    public void update(BusiReservationCardPage user) {
        cardDao.update(user);
    }
    @Override
    public void delete(Long userId) {
        cardDao.delete(userId);
    }
    @Override
    public void deleteBatch(Long[] userIds) {
        cardDao.deleteBatch(userIds);
    }

    @Override
    public List<BusiReservationCardPage> queryObjectByPage(BusiReservationCardPage cardPage) {
        return cardDao.queryObjectByPage(cardPage);
    }
}
