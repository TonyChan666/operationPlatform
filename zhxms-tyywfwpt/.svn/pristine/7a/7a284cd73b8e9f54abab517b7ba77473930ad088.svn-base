package cn.com.bmsoft.modules.am.controller;

import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.service.IndexStatisticsService;
import cn.com.bmsoft.modules.am.service.RecordService;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.modules.epm.entity.EmergencePlanTaskEntity;
import cn.com.bmsoft.modules.epm.service.EmergencePlanService;
import cn.com.bmsoft.modules.epm.service.EmergencePlanTaskService;
import cn.com.bmsoft.modules.sys.controller.AbstractController;
import cn.com.bmsoft.modules.wom.entity.InformationEntity;
import cn.com.bmsoft.modules.wom.service.HandleInformationService;
import cn.com.bmsoft.modules.wom.service.InformationService;
import cn.com.bmsoft.utils.R;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通运维人员首页统计数据
 *
 * @author zdh on 2019/10/23
 */
@RestController
@RequestMapping("am/index")
@Api(" API")
public class IndexStatisticsController extends AbstractController {

    @Autowired
    private EmergencePlanService emergencePlanService;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private EmergencePlanTaskService emergencePlanTaskService;
    
    @Autowired
    private HandleInformationService handleInformationService;
    
    @Autowired
    private IndexStatisticsService orderStatisticsService;
    
    @Autowired
    private IndexStatisticsService indexStatisticsService;
    
   
    

     /**
      * @Author:         zdh on 2019/10/23 15:05
      * @param:          id
      * @return:         R
      * @Description:   普通运维人员统计数据
      *
      */
    @GetMapping("/commonData")
    //@RequiresPermissions("am:record:list")
    @ApiOperation(value = "普通运维人员首页统计数据", notes = "获取普通运维人员首页统计数据", response = R.class)
    public R commonData(){
    	Long userId = getUserId();
        int unDealWithCount = 0; 
        int workOrderCount = 0;   
        int resourceServiceCount = 0;  
        int businessServicesCount = 0;  
        Map<String, Integer> countData = new HashMap<>();
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("userId", userId);
        
        //待办预案数量(当前用户待审评的预案数量)
        int emergencePlanCount = emergencePlanTaskService.count(new QueryWrapper<EmergencePlanTaskEntity>().eq("update_user", userId));
        
        //待办工单数量(当前用户需要处理的工单)
        int orderCount = informationService.count(new QueryWrapper<InformationEntity>().eq("ywryid", userId).ne("gdzt", "0").ne("gdzt", "5").isNull("delete_flag"));
        
        //待办告警数量(当前用户需要处理的告警信息)
        int alarmCount = recordService.count(new QueryWrapper<RecordEntity>().eq("handle_user_id", userId).isNull("order_code").isNull("delete_flag"));
        
        //所有待办数量
        unDealWithCount=emergencePlanCount+orderCount+alarmCount;
        
        //当前用户申请的工单数量
        workOrderCount = informationService.count(new QueryWrapper<InformationEntity>().eq("cjrid", userId).isNull("delete_flag"));
        
        //当前用户维护的资源服务数量
        resourceServiceCount= indexStatisticsService.queryResourceByUserId(queryParam);
        
        //当前用户维护的业务服务数量
        businessServicesCount = serviceService.count(new QueryWrapper<ServiceEntity>().eq("ywryid", userId).isNull("delete_flag"));
        
        
        countData.put("unDealWithCount", unDealWithCount);
        countData.put("workOrderCount", workOrderCount);
        countData.put("resourceServiceCount", resourceServiceCount);
        countData.put("businessServicesCount", businessServicesCount);
        return R.ok().put("data",countData);
    }

    @GetMapping("/operatorData")
    //@RequiresPermissions("am:record:list")
    @ApiOperation(value = "运营商管理人员首页统计数据", notes = "获取运营商管理人员首页统计数据", response = R.class)
    public R operatorData(){
    	Long userId = getUserId();
    	
        Map<String, Integer> countData = new HashMap<>();
        Map<String, Object> queryParam = new HashMap<>();
        
        queryParam.put("userId", userId);

        int sendUnDealWithCount = 0;  
        int verifyOrderCount = 0;     
        int unDealAlarmCount = 0;     
        int unDealOrderCount = 0;     
          
        //派发代办
        sendUnDealWithCount = informationService.count(new QueryWrapper<InformationEntity>().eq("cjrid", userId).ne("gdzt", "0").ne("gdzt", "5").isNull("delete_flag"));
        
        //审核代办
        verifyOrderCount = informationService.count(new QueryWrapper<InformationEntity>().eq("ywryid", userId).ne("gdzt", "0").ne("gdzt", "5").isNull("delete_flag"));
        
        //当前用户登记的资源发生的未解决告警记录数量
        unDealAlarmCount = indexStatisticsService.queryAlarmCountByUserId(queryParam);
        
        //当前用户登记的资源发生的未解决工单记录数量
        unDealOrderCount = indexStatisticsService.queryOrderCountByUserId(queryParam);
        
        
        countData.put("sendUnDealWithCount", sendUnDealWithCount); 
        countData.put("verifyOrderCount", verifyOrderCount);
        countData.put("unDealAlarmCount", unDealAlarmCount);
        countData.put("unDealOrderCount", unDealOrderCount);
        return  R.ok().put("data",countData);
    }

    @GetMapping("/orderCount")
    //@RequiresPermissions("am:record:list")
    @ApiOperation(value = "工单趋势数量数据", notes = "获取工单趋势数量数据", response = R.class)
    public R orderCount(@RequestParam Map<String, Object> params) throws ParseException{
    	Long userId = getUserId();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar= Calendar.getInstance();

    	params.put("userId", userId);
    	
    	Date startDate = new Date();
    	Date endDate = new Date();
    	
    	calendar.setTime(new Date());
    	calendar.add(Calendar.DATE, -7);
    	startDate = calendar.getTime();

    	
    	if(null==params.get("startDate")){
    		String startDateStr =sdf.format(startDate);
    		params.put("startDate", startDateStr);
    	}else{
    		startDate=sdf.parse(params.get("startDate").toString());
    		calendar.setTime(startDate);
    		calendar.add(Calendar.DATE, -1);
    		startDate=calendar.getTime();
    		String startDateStr =sdf.format(startDate);
    		params.remove("startDate");
    		params.put("startDate", startDateStr);
    	}
    	
    	if(null==params.get("endDate")){
    		String endDateStr =sdf.format(endDate);
    		params.put("endDate", endDateStr);
    	}
    	
    	List<Map<String, Object>> orderCountList =indexStatisticsService.queryTrendOrderCount(params);
        return  R.ok().put("data",orderCountList);
    }

    @GetMapping("/resourcePercent")
    //@RequiresPermissions("am:record:list")
    @ApiOperation(value = "管理资源占比分布", notes = "获取管理资源占比分数据", response = R.class)
    public R resourcePercent(Map<String, Object> queryParam){
    	Long userId = getUserId();
    	queryParam.put("userId", userId);
    	//当前用户维护的资源服务数量
    	List<Map<String, Object>> resouecePercentList =indexStatisticsService.queryResourcePercent(queryParam);

        return  R.ok().put("data",resouecePercentList);
    }
    
    @GetMapping("/adminData")
    //@RequiresPermissions("am:record:list")
    @ApiOperation(value = "系统管理员首页统计数据", notes = "系统管理员首页统计数据", response = R.class)
    public R adminData(){
        Map<String, Object> countData = new HashMap<>();
        int orderCount=informationService.count(new QueryWrapper<InformationEntity>().isNull("delete_flag"));
        int alarmCount=recordService.count(new QueryWrapper<RecordEntity>().isNull("delete_flag"));
        countData.put("orderCount", orderCount);
        countData.put("alarmCount", alarmCount);
        return  R.ok().put("data",countData);
    }

}
