package com.platform.service;

import com.platform.model.page.BusiReservationCardPage;

import java.util.List;
import java.util.Map;

public interface ApiCardService {
    public BusiReservationCardPage queryObject(String cardCode);

    public List<BusiReservationCardPage> queryList(Map<String, Object> map);

    public int queryTotal(Map<String, Object> map);

    public void save(String mobile, String password);

    public void save(BusiReservationCardPage userVo);
    public void update(BusiReservationCardPage user);
    public void delete(Long userId);

    public void deleteBatch(Long[] userIds);

    List<BusiReservationCardPage> queryObjectByPage(BusiReservationCardPage page);
}
