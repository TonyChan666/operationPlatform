package cn.com.bmsoft.modules.am.controller;

import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.AmStatusService;
import cn.com.bmsoft.modules.am.service.RecordService;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.entity.SystemEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.bsm.service.SystemService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.modules.wom.entity.InformationEntity;
import cn.com.bmsoft.modules.wom.service.InformationService;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("am/amstatus")
@Api("系统管理员首页告警数据 API")
public class AmStatusController extends AbstractController {

    @Autowired
    private AmStatusService amStatusService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private ServiceService serviceService;

    @RequestMapping("/link")
    public R link(){
        Map<String, Integer> result = new HashMap<>();
        result.put("status", amStatusService.link());
        return R.ok().put("data",result);
    }

    @RequestMapping("/adminData")
    public R adminData(){
        Map<String, Object> countData = new HashMap<>();
        //获取未办结的工单
        int orderCount=informationService.count(new QueryWrapper<InformationEntity>().isNull("delete_flag").ne("gdzt","5"));
        //告警服务数
        List<String> bsmTableName = new ArrayList<>();
        bsmTableName.add("bsm_service");bsmTableName.add("bsm_system");
        List alarmCount=recordService.list(new QueryWrapper<RecordEntity>().select("max(id)").isNull("delete_flag").eq("alarm_record_type","2").in("resource_type",bsmTableName).groupBy("alarm_object","resource_type"));
        //业务系统数
        int system_count = systemService.count(new QueryWrapper<SystemEntity>().isNull("delete_flag"));
        //业务服务数
        int service_count = serviceService.count(new QueryWrapper<ServiceEntity>().isNull("delete_flag"));
        countData.put("order_count", orderCount);
        countData.put("service_am_count", alarmCount.size());
        countData.put("service_total",system_count + service_count);
        return  R.ok().put("data",countData);
    }

    @RequestMapping("/intranet")
    public R intranet(){
        List<Map<String, Object>> result = amStatusService.intranet();
        return R.ok().put("data",result);
    }

    @RequestMapping("/amRecord")
    public R amRecord(@RequestParam Map<String, Object> params){
        String dict_name = params.get("dict_code")+"";
        String dict_value = params.get("value")+"";
        if(StringUtil.isEmpty(dict_name) || StringUtil.isEmpty(dict_value)) {
            return R.error("请输入告警类型参数");
        }
        return R.ok().put("data",amStatusService.amRecord(params));
    }

    @RequestMapping("/extranet")
    public R extranet(){
        //暂时返回假数据
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name","民生开发服务");
        map.put("status",0);
        result.add(map);
        map = new HashMap<>();
        map.put("name","可信身份认证应用审核服务");
        map.put("status",0);
        result.add(map);
        map = new HashMap<>();
        map.put("name","民生数据跨网交换（准入服务）API");
        map.put("status",0);
        result.add(map);
        map = new HashMap<>();
        map.put("name","其他民生应用");
        map.put("status",1);
        result.add(map);
        return R.ok().put("data",result);
    }

}
