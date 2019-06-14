package com.platform.service.impl;

import com.platform.dao.ApiMCardmessageDao;
import com.platform.dao.MCardmessageDao;
import com.platform.entity.BReservationcardEntity;
import com.platform.entity.MCardmessageEntity;
import com.platform.service.BReservationcardService;
import com.platform.service.MCardmessageService;
import com.platform.utils.IdUtil;
import com.platform.utils.R;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 消息提示表Service实现类
 *
 * @author lipengjun
 * @date 2019-06-13 11:48:48
 */
@Service("mCardmessageService")
@Transactional
public class MCardmessageServiceImpl implements MCardmessageService {
    @Autowired
    private MCardmessageDao mCardmessageDao;
    @Autowired
    private BReservationcardService bReservationcardService;
    @Override
    public MCardmessageEntity queryObject(String id) {
        return mCardmessageDao.queryObject(id);
    }

    @Override
    public List<MCardmessageEntity> queryList(Map<String, Object> map) {
        return mCardmessageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return mCardmessageDao.queryTotal(map);
    }

    @Override
    public int save(MCardmessageEntity mCardmessage) {
        mCardmessage.setId(IdUtil.createIdbyUUID());
        return mCardmessageDao.save(mCardmessage);
    }

    @Override
    public int update(MCardmessageEntity mCardmessage) {
        return mCardmessageDao.update(mCardmessage);
    }

    @Override
    public int delete(String id) {
        return mCardmessageDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return mCardmessageDao.deleteBatch(ids);
    }

    @Override
    //卡片激活
    public R activite(List<String> ids) {
        try{
            if(ids != null && ids.size()>0){
                MCardmessageEntity mCardmessage=new MCardmessageEntity();
                BReservationcardEntity cardEntity=new BReservationcardEntity();
                for(int i=0;i<ids.size();i++){
                    MCardmessageEntity mCardmessageEntity = this.queryObject(ids.get(i));
                    //为0的才激活
                    if(StringUtils.equals(mCardmessageEntity.getCardstatus(),"0")) {
                        String cardcode = mCardmessageEntity.getCardcode();
                        cardEntity.setCardcode(cardcode);
                        cardEntity.setCardstatus("1");
                        bReservationcardService.updateByCardCode(cardEntity);
                        mCardmessage.setId(ids.get(i));
                        mCardmessage.setStatus("0");
                        this.update(mCardmessage);
                    }
                }
            }
            return R.ok();
        }catch (Exception e){
            return R.error();
        }
    }

    @Override
    public R delay(Map<String, Object> params) {
        List<String> ids = (ArrayList)params.get("ids");
        Integer delayMonth= params.get("delayMonth") == null ? null:Integer.valueOf(params.get("delayMonth").toString());
        try{
            if(ids != null && ids.size()>0){
                MCardmessageEntity mCardmessage=new MCardmessageEntity();
                BReservationcardEntity cardEntity=new BReservationcardEntity();
                for(int i=0;i<ids.size();i++){
                    MCardmessageEntity mCardmessageEntity = this.queryObject(ids.get(i));
                    if(StringUtils.equals(mCardmessageEntity.getCardstatus(),"4")) {
                        String cardcode = mCardmessageEntity.getCardcode();
                        cardEntity.setCardcode(cardcode);
                        cardEntity.setCardstatus("1");
                        Date endDate = cardEntity.getEndDate();
                        if (endDate == null) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            calendar.add(Calendar.MONTH, delayMonth);
                            cardEntity.setEndDate(calendar.getTime());
                        }
                        bReservationcardService.updateByCardCode(cardEntity);
                        mCardmessage.setId(ids.get(i));
                        mCardmessage.setStatus("0");
                        this.update(mCardmessage);
                    }
                }
            }
            return R.ok();
        }catch (Exception e){
            return R.error();
        }
    }
}
