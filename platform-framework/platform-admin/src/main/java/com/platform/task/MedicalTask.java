package com.platform.task;

import com.platform.entity.BReservationcardEntity;
import com.platform.entity.SysUserEntity;
import com.platform.service.BReservationcardService;
import com.platform.service.SysUserService;
import com.platform.utils.Constant;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 *
 * medicalTask为spring bean的名称
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
@Component("medicalTask")
public class MedicalTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BReservationcardService bReservationcardService;

    public void updateCardStatus() {
        List<BReservationcardEntity> bReservationcardEntities = bReservationcardService.queryList(null);
        if(bReservationcardEntities != null && bReservationcardEntities.size()>0){
            for (BReservationcardEntity entity :
            bReservationcardEntities) {
                Date endDate = entity.getEndDate();
                if(endDate != null && new Date().after(endDate)){
                    entity.setCardstatus("4");
                    bReservationcardService.update(entity);
                }
            }
        }

    }


    public void test2() {
        logger.info("我是不带参数的test2方法，正在被执行");
    }
}
