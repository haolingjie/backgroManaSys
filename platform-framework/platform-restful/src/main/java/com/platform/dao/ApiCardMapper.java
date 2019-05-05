package com.platform.dao;

import com.platform.entity.TokenEntity;
import com.platform.model.page.BusiReservationCardPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Token
 *
 * @author lipengjun
 * @date 2017年11月20日 下午3:29:40
 */
public interface ApiCardMapper extends BaseDao<BusiReservationCardPage> {

    List<BusiReservationCardPage> queryObjectByPage(BusiReservationCardPage page);
}
