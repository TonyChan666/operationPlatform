package cn.com.bmsoft.modules.rm.service.impl;

import cn.com.bmsoft.modules.rm.dao.NetworkDeviceDao;
import cn.com.bmsoft.modules.rm.entity.NetworkDeviceEntity;
import cn.com.bmsoft.modules.rm.entity.RmResourceDeptEntity;
import cn.com.bmsoft.modules.rm.service.NetworkDeviceService;
import cn.com.bmsoft.modules.rm.service.RmResourceDeptService;
import cn.com.bmsoft.modules.rm.utils.RmParams;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("networkDeviceService")
public class NetworkDeviceServiceImpl extends ServiceImpl<NetworkDeviceDao, NetworkDeviceEntity> implements NetworkDeviceService {

    @Autowired
    private NetworkDeviceDao networkDeviceDao;

    @Autowired
    private RmResourceDeptService rmResourceDeptService;

    final static String[] SEARCH_FIELDS = {"sbmc","sblb","wllb","ip","ywryid","sfyx"};

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NetworkDeviceEntity> page = new Query<NetworkDeviceEntity>().getPage(params);

        return new PageUtils(networkDeviceDao.getNetworkDeviceList(page,params));
    }

    @Override
    public NetworkDeviceEntity getNetworkDevice(Integer id) {
        NetworkDeviceEntity networkDevice = networkDeviceDao.getNetworkDevice(id);
        if(networkDevice == null) {
            return null;
        }
        List<Map<String, Object>> resourceDeptNames = rmResourceDeptService.getResourceDeptNames(networkDevice.getId() + "", RmParams.RM_TABLE_NAME.get(RmParams.NETWORKDEVICE_ENTITY_NAME));
        List<Integer> ywzids = new ArrayList<>();
        String ywzmc = "";
        for (Map<String, Object> map : resourceDeptNames) {
            ywzids.add(Integer.parseInt(map.get("DEPT_ID")+""));
            ywzmc = (StringUtil.isEmpty(ywzmc)?"":(ywzmc+",")) + (StringUtil.isEmpty(map.get("DEPT_NAME")+"")?"":map.get("DEPT_NAME"));
        }
        networkDevice.setYwzid(ywzids);
        networkDevice.setYwzmc(ywzmc);
        return networkDevice;

    }

    @Override
    @Transactional
    public void saveNetworkDevice(NetworkDeviceEntity networkDevice) {
        this.save(networkDevice);
        List<Integer> ywzids = networkDevice.getYwzid();
        if(!ywzids.isEmpty()) {
            List<RmResourceDeptEntity> rmResourceDeptEntities = new ArrayList<>();
            for (Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDeptEntity = new RmResourceDeptEntity();
                rmResourceDeptEntity.setZyid(networkDevice.getId());
                rmResourceDeptEntity.setYwzid(ywzid);
                rmResourceDeptEntity.setZybm(RmParams.RM_TABLE_NAME.get(RmParams.NETWORKDEVICE_ENTITY_NAME));
                rmResourceDeptEntities.add(rmResourceDeptEntity);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntities);
        }
    }

    @Override
    public void removeToRecoveryByIds(List<Integer> ids) {
        networkDeviceDao.removeToRecoveryByIds(ids);
    }

    @Override
    @Transactional
    public R updateNetworkDevice(NetworkDeviceEntity networkDeviceEntity) {
//        if(networkDeviceEntity.getSbbh() != null ) {
//            if(networkDeviceEntity.getSbbh().length() != 5) {
//                return R.error("请输入五位数网络设备编码");
//            }else{
//                //设备编号等于"WSSB"+五位编码
//                networkDeviceEntity.setSbbh(RmParams.CODE_PREFIX.get(RmParams.NETWORKDEVICE_ENTITY_NAME) + networkDeviceEntity.getSbbh());
//            }
//        }
        String tableName = RmParams.RM_TABLE_NAME.get(RmParams.NETWORKDEVICE_ENTITY_NAME);
        List<Integer> ywzids = networkDeviceEntity.getYwzid();
        List<RmResourceDeptEntity> rmResourceDeptEntitieList = new ArrayList<>();
        rmResourceDeptService.removeResourceDepts(networkDeviceEntity.getId()+"", tableName);
        if(!ywzids.isEmpty()){
            for(Integer ywzid : ywzids) {
                RmResourceDeptEntity rmResourceDept = new RmResourceDeptEntity();
                rmResourceDept.setYwzid(ywzid);
                rmResourceDept.setZyid(networkDeviceEntity.getId());
                rmResourceDept.setZybm(tableName);
                rmResourceDeptEntitieList.add(rmResourceDept);
            }
            rmResourceDeptService.saveResourceDepts(rmResourceDeptEntitieList);
        }
        this.updateById(networkDeviceEntity);
        return R.ok();
    }

}