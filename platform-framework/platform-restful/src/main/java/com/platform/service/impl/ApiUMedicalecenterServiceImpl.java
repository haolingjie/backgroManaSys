package com.platform.service.impl;

import com.platform.dao.ApiUMedicalecenterDao;
import com.platform.entity.UMedicalecenterEntity;
import com.platform.entity.vo.MedicalCenterVO;
import com.platform.service.ApiUMedicalecenterService;
import com.platform.utils.IdUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 套餐明细表Service实现类
 *
 * @author lipengjun
 * @date 2019-04-26 18:24:29
 */
@Service("apiUMedicalecenterService")
public class ApiUMedicalecenterServiceImpl implements ApiUMedicalecenterService {
    @Autowired
    private ApiUMedicalecenterDao uMedicalecenterDao;

    @Override
    public UMedicalecenterEntity queryObject(String id) {
        return uMedicalecenterDao.queryObject(id);
    }

    @Override
    public List<UMedicalecenterEntity> queryList(Map<String, Object> map) {
        return uMedicalecenterDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return uMedicalecenterDao.queryTotal(map);
    }

    @Override
    public int save(UMedicalecenterEntity uMedicalecenter) {
        uMedicalecenter.setId(IdUtil.createIdbyUUID());
        return uMedicalecenterDao.save(uMedicalecenter);
    }

    @Override
    public int update(UMedicalecenterEntity uMedicalecenter) {
        return uMedicalecenterDao.update(uMedicalecenter);
    }

    @Override
    public int delete(String id) {
        return uMedicalecenterDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return uMedicalecenterDao.deleteBatch(ids);
    }

    /**
     * 查询城市，地区代码
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> queryCenterAddress() throws Exception{
        Map<String, Object> addressMap = new HashMap<>();
        List<UMedicalecenterEntity> citycenterEntities = uMedicalecenterDao.queryCityAddress();
        List<MedicalCenterVO> cityCenterVOS = new ArrayList<MedicalCenterVO>();
        if(citycenterEntities != null && citycenterEntities.size()>0){
            for (UMedicalecenterEntity entity:citycenterEntities) {
                MedicalCenterVO medicalCenterVO = new MedicalCenterVO();
                BeanUtils.copyProperties(medicalCenterVO,entity);
                cityCenterVOS.add(medicalCenterVO);
            }
        }
        List<UMedicalecenterEntity> areacenterEntities = uMedicalecenterDao.queryAreaAddress();
        List<MedicalCenterVO> areaCenterVOS = new ArrayList<MedicalCenterVO>();
        if(areacenterEntities != null && areacenterEntities.size()>0){
            for (UMedicalecenterEntity entity:areacenterEntities) {
                MedicalCenterVO medicalCenterVO = new MedicalCenterVO();
                BeanUtils.copyProperties(medicalCenterVO,entity);
                areaCenterVOS.add(medicalCenterVO);
            }
        }
        addressMap.put("cityCenterVOS",cityCenterVOS);
        addressMap.put("areaCenterVOS",areaCenterVOS);
        return addressMap;
    }

}
