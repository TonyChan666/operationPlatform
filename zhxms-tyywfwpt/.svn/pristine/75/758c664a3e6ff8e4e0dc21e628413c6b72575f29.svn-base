package cn.com.bmsoft.modules.sa.service.impl;

import cn.com.bmsoft.modules.sa.dao.SaWomDao;
import cn.com.bmsoft.modules.sa.service.SaWomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("saWomService")
public class SaWomServiceImpl implements SaWomService {

    @Autowired
    private SaWomDao saWomDao;

    @Override
    public List<Map<String, Object>> quantityTrendOfWorkOrder(Map<String, Object> queryParam) {
        return saWomDao.quantityTrendOfWorkOrder(queryParam);
    }

    @Override
    public List<Map<String, Object>> workOrderOfLastMonth(Map<String, Object> queryParam) {
        return saWomDao.workOrderOfLastMonth(queryParam);
    }

    @Override
    public List<Map<String, Object>> workOrderResolutionRateOfLastMonth(Map<String, Object> queryParam) {
        return saWomDao.workOrderResolutionRateOfLastMonth(queryParam);
    }

    @Override
    public List<Map<String, Object>> proportionOfWorkOrderType(Map<String, Object> queryParam) {
        return saWomDao.proportionOfWorkOrderType(queryParam);
    }

    @Override
    public List<Map<String, Object>> workOrderCreaterOfTopTen(Map<String, Object> queryParam) {
        return saWomDao.workOrderCreaterOfTopTen(queryParam);
    }

    @Override
    public List<Map<String, Object>> workOrder(Map<String, Object> queryParam){
        return saWomDao.workOrder(queryParam);
    }
}
