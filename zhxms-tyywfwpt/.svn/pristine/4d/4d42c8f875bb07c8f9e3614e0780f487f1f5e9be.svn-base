package cn.com.bmsoft.modules.am.service.impl;

import cn.com.bmsoft.modules.am.dao.AmStatusDao;
import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.AmStatusService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("amStatusService")
public class AmStatusServiceImpl implements AmStatusService {

    /**
     * 告警状态
     */
    private final static Integer ALARM_STATUS = 2;
    /**
     * 异常状态
     */
    private final static Integer EXCEPTION_STATUS = 1;
    /**
     * 正常状态
     */
    private final static Integer MORMAL_STATUS = 0;

    @Autowired
    AmStatusDao amStatusDao;

    @Override
    public Integer link() {
        return amStatusDao.link() == 0 ? MORMAL_STATUS:EXCEPTION_STATUS;
    }

    @Override
    public List<Map<String, Object>> intranet() {
        //获取字典值，作为最后数据返回
        List<Map<String, Object>> result = amStatusDao.getDictItem();

        //处理各个字典值对应的状态
        //获取存储资源的状态
        List<Map<String,Object>> storageData = amStatusDao.getStorageStatus();
        List<Map<String,Object>> serviceData = amStatusDao.getServiceStatus();
        for(Map<String, Object> data : result){
            //存储资源的状态
            for(Map<String,Object> storage : storageData) {
                //存储资源对应的告警数量不为0
                if(storage.get("count") != null && !"0".equals(storage.get("count")+"")
                        && (storage.get("zylb")+"").equals(data.get("value")+"")
                        && "3".equals(data.get("type")+"")){
                    //将状态置为2，标识告警
                    data.put("status", ALARM_STATUS);
                }
            }
            //获取服务的状态
            for(Map<String,Object> service : serviceData) {
                //业务服务,服务的业务服务与字典值对上，且当前状态不为告警的情况下，更新状态为服务的运行状态
                if(service.get("yxzt") != null && !(ALARM_STATUS+"").equals(data.get("status")) &&
                        (service.get("ywjz")+"").equals(data.get("value")+"") && "2".equals(data.get("type")+"")){
                    //将状态置为服务的运行状态，标识告警或异常
                    data.put("status", Integer.parseInt(service.get("yxzt")+""));
                }
                //支撑服务,服务的支撑服务与字典值对上，且当前状态不为告警的情况下，更新状态为服务的运行状态
                if(service.get("yxzt") != null && !(ALARM_STATUS+"").equals(data.get("status")) &&
                        (service.get("zcfwlx")+"").equals(data.get("value")+"") && "1".equals(data.get("type")+"")){
                    //将状态置为服务的运行状态，标识告警或异常
                    data.put("status", Integer.parseInt(service.get("yxzt")+""));
                }
            }
        }

        return result;
    }

    @Override
    public PageUtils amRecord(Map<String, Object> params) {
        IPage<RecordEntity> page = new Query<RecordEntity>().getPage(params);
        String dict_name = params.get("dict_code")+"";
        String dict_value = params.get("value")+"";
        params.put(dict_name,dict_value);
        return new PageUtils(amStatusDao.getAmRecord(page,params));
    }
}
