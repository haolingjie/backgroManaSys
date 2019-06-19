package com.platform.dao;

import com.platform.entity.BReservationcardEntity;

import java.util.List;

/**
 * 预约卡信息表Dao
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
public interface BReservationcardDao extends BaseDao<BReservationcardEntity> {

    void saveList(List<BReservationcardEntity> cardList);

    void updateByCardCode(BReservationcardEntity entity);

    List<BReservationcardEntity> queryByEntity(BReservationcardEntity entity);
}
