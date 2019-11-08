package cn.com.bmsoft.modules.am.controller;

import io.swagger.annotations.Api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.com.bmsoft.modules.am.entity.AmStrategyEntity;
import cn.com.bmsoft.modules.am.entity.AppRunningEntity;
import cn.com.bmsoft.modules.am.entity.MachineRunningEntity;
import cn.com.bmsoft.modules.am.entity.ModelEntity;
import cn.com.bmsoft.modules.am.entity.RecordEntity;
import cn.com.bmsoft.modules.am.entity.ThresholdGradeEntity;
import cn.com.bmsoft.modules.am.service.AmStrategyService;
import cn.com.bmsoft.modules.am.service.AppRunningService;
import cn.com.bmsoft.modules.am.service.MachineRunningService;
import cn.com.bmsoft.modules.am.service.ModelService;
import cn.com.bmsoft.modules.am.service.RecordService;
import cn.com.bmsoft.modules.am.service.ThresholdGradeService;
import cn.com.bmsoft.modules.bsm.entity.ServiceEntity;
import cn.com.bmsoft.modules.bsm.entity.SystemEntity;
import cn.com.bmsoft.modules.bsm.service.ServiceService;
import cn.com.bmsoft.modules.bsm.service.SystemService;
import cn.com.bmsoft.modules.rm.entity.ServerEntity;
import cn.com.bmsoft.modules.rm.service.ServerService;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.wom.entity.InformationEntity;
import cn.com.bmsoft.modules.wom.entity.ProcessEntity;
import cn.com.bmsoft.modules.wom.service.BusinessBindingService;
import cn.com.bmsoft.modules.wom.service.InformationService;
import cn.com.bmsoft.modules.wom.service.ProcessService;
import cn.com.bmsoft.workflowInterface.RestTemplate;

@RestController
@RequestMapping("am/strategyRun")
@Api("告警策略表分析 API")
public class StrategyRunConroller {
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private AmStrategyService amStrategyService;

	@Autowired
	private ServerService serverService;

	@Autowired
	private MachineRunningService machineRunningService;

	@Autowired
	private ThresholdGradeService thresholdGradeService;

	@Autowired
	private RecordService recordService;

	@Autowired
	private InformationService informationService;

	@Autowired
	private SerialNumberService serialNumberService;
	
	@Autowired
    private ModelService modelService;
	
	@Autowired
	private ServiceService serviceService;
	
    @Autowired
    private AppRunningService appRunningService;
    
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private ProcessService processService;
    
    @Autowired
    private BusinessBindingService rModelService;
    
    /**
     * 服务器告警策略执行
     * @throws Exception
     */
	@GetMapping("/runMachineStrategy")
	public void runMachineStrategy() throws Exception {
		AmStrategyEntity queryAmStrategyEntity = new AmStrategyEntity();
		queryAmStrategyEntity.setAlarmProjectType("1");

		QueryWrapper<AmStrategyEntity> amStrategyQueryWrapper = new QueryWrapper<AmStrategyEntity>();

		amStrategyQueryWrapper.eq("alarm_project_type", "1");
		List<AmStrategyEntity> strategyList = amStrategyService.list(amStrategyQueryWrapper);

		for (AmStrategyEntity strategyEntity : strategyList) {
			// 资源为服务器
			if ("1".equals(strategyEntity.getResourceType())) {

				String[] resourceArr = strategyEntity.getResourceList().split(",");
				for (String resourceCode : resourceArr) {
					// 查询到对应的服务器信息
					QueryWrapper<ServerEntity> serverEntityWrapper = new QueryWrapper<ServerEntity>();
					serverEntityWrapper.eq("fwqbm", resourceCode);
					ServerEntity serverEntity = serverService.getOne(serverEntityWrapper);

					// 查询对应的服务器最新指标项目对应的数值
					QueryWrapper<MachineRunningEntity> machineRunningWrapper = new QueryWrapper<MachineRunningEntity>();
					machineRunningWrapper.eq("machine_code",serverEntity.getFwqbm());
					machineRunningWrapper.orderByDesc("create_time");
					List<MachineRunningEntity> machineRunningList = (List<MachineRunningEntity>) machineRunningService.list(machineRunningWrapper);
					MachineRunningEntity machineRunningEntity = machineRunningList.get(0);
					
					float targetValue = 0;
					
					if ("1".equals(strategyEntity.getTargetCode())) {//指标 为 CPU使用率
						targetValue=Float.parseFloat(machineRunningEntity.getCpuUsePencent());
					}
					if ("2".equals(strategyEntity.getTargetCode())) {//指标 为 内存使用率
						targetValue=Float.parseFloat(machineRunningEntity.getMemUsePencent());
					}
					if ("3".equals(strategyEntity.getTargetCode())) {//指标 为 存储空间使用率
						targetValue=Float.parseFloat(machineRunningEntity.getRomUsePencent());
					}
					

					// 通过告警策略找到对应的阈值规则记录,把读取到的机器参数和阈值从高到底进行对比
					QueryWrapper<ThresholdGradeEntity> thresholdGradeEntityWrapper = new QueryWrapper<ThresholdGradeEntity>();
					thresholdGradeEntityWrapper.eq("strategy_id",strategyEntity.getId());
					thresholdGradeEntityWrapper.orderByDesc("threshold_level");
					List<ThresholdGradeEntity> thresholdGradeEntityList = (List<ThresholdGradeEntity>) thresholdGradeService.list(thresholdGradeEntityWrapper);

					// 阈值规则从高级到低级依次遍历
					for (int i = 0; i < thresholdGradeEntityList.size(); i++) {
						ThresholdGradeEntity thresholdGradeEntity = thresholdGradeEntityList.get(i);
						Float thresholdValue = Float.parseFloat(thresholdGradeEntity.getThreshold()); // 阈值
						String operation = thresholdGradeEntity.getCondtion(); // 对比条件
						String str = targetValue + operation + thresholdValue; // 对比表达式
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
						boolean operationResult = (boolean) engine.eval(str);
						// 如果operationResult为true,则说明触发了报警条件
						if (operationResult) {
							//按顺序从高到底依次查询是否有对应的未处理的告警记录,如果已经存在未处理的告警记录则不再生成告警记录
							QueryWrapper<RecordEntity> recordEntityWrapper = new QueryWrapper<RecordEntity>();
							recordEntityWrapper.eq("alarm_object", resourceCode);
							recordEntityWrapper.eq("alarm_record_type", "1");
							recordEntityWrapper.eq("resource_type", "1");
							recordEntityWrapper.eq("alarm_grade", thresholdGradeEntity.getThresholdLevel());
							recordEntityWrapper.eq("alarm_target", strategyEntity.getTargetCode());

							
							List<RecordEntity> RecordEntityList = (List<RecordEntity>) recordService.list(recordEntityWrapper);
                            if(RecordEntityList.size()>0){
                            	break;
                            }
                            
							// 生成告警记录
							RecordEntity recordEntity = new RecordEntity();

							recordEntity.setAlarmRecordType("1"); // 告警记录类型
							recordEntity.setResourceType("1"); // 告警资源类型
							recordEntity.setAlarmObject(resourceCode); // 告警服务器编码
							recordEntity.setAlarmTarget(strategyEntity.getTargetCode()); // 告警指标编码
							recordEntity.setAlarmValue(targetValue + ""); // 告警值
							recordEntity.setAlarmTime(new Date()); // 告警时间
							recordEntity.setIpAddress(machineRunningEntity.getIpAddress()); // 告警服务器IP地址

							recordEntity.setNetworkType(serverEntity.getWllb()); // 服务器所属网络
							recordEntity.setPoliceType(serverEntity.getYwjz()); // 服务器所属警种
							recordEntity.setHandleUserId(Long.parseLong(serverEntity.getYwryid() + "")); // 服务器运维人员

							recordEntity.setAlarmGrade(thresholdGradeEntity.getThresholdLevel()); // 告警级别
							recordEntity.setAlarmNoticeType(thresholdGradeEntity.getAlarmType()); // 告警通知方式
							recordEntity.setTimeLimit(thresholdGradeEntity.getRequirestHandleTime());//要求处理时间
 
							recordEntity.setHandleStatus("0");
							
							cn.com.bmsoft.utils.R result = serialNumberService.generator("YAPG");
							String recordCode = (String) result.get("serialNumber");
                            recordEntity.setAlarmRecordCode(recordCode);
							
							if (thresholdGradeEntity.getIsOrder() == 1) {
								InformationEntity informationEntity = new InformationEntity();
								String orderTitle = serverEntity.getSbmc()
										+ "  发生  "
										+ strategyEntity.getTargetCode()
										+ "  告警 ";
								String orderContent = serverEntity.getSbmc()
										+ "  发生 "
										+ strategyEntity.getTargetCode()
										+ "  告警,告警级别为:"
										+ thresholdGradeEntity.getThresholdLevel() 
										+ ",告警值为:"+ targetValue;

								informationEntity.setGdbt(orderTitle);
								informationEntity.setGdbsnr(orderContent);
								informationEntity.setGdssfwid(serverEntity.getFwqbm());
								informationEntity.setGdssfwmc(serverEntity.getSbmc());
								informationEntity.setYxj("1");
								informationEntity.setGdlx("1");
								informationEntity.setGdzt("2");
								informationEntity.setSftj(1);
								informationEntity.setCjsj(new Date());

								//String orderCode = savaOrder(informationEntity); //生成工单,并返回工单ID
								//recordEntity.setOrderCode(orderCode);
								
							    } 
                                if (thresholdGradeEntity.getAlarmType().equals("1")) {
								
								  //String modelContent = createModelContent(thresholdGradeEntity.getEmailModeId(),recordEntity,serverEntity.getSbmc());
								
								  // 发送短信
							  }
							   if (thresholdGradeEntity.getAlarmType().equals("2")) {
								   
								  //String modelContent = createModelContent(thresholdGradeEntity.getMessageModelId(),recordEntity,serverEntity.getSbmc());

								 // 发送邮件
							}
							recordService.save(recordEntity);
						}

						if (operationResult) {
							break;
						}

					}

				}
			} else {

			}
		}
	}
	
	/**
     * 应用告警策略执行
     * @throws Exception
     */
	@GetMapping("/runServiceStrategy")
	public void runAppStrategy() throws Exception {
		
		QueryWrapper<AmStrategyEntity> amStrategyQueryWrapper = new QueryWrapper<AmStrategyEntity>();
		amStrategyQueryWrapper.eq("alarm_project_type", "2");
		List<AmStrategyEntity> strategyList = amStrategyService.list(amStrategyQueryWrapper);

		for (AmStrategyEntity strategyEntity : strategyList) {
		
				String[] resourceArr = strategyEntity.getResourceList().split(",");
				for (String resourceCode : resourceArr) {
					// 查询到对应的应用信息
					QueryWrapper<ServiceEntity> serviceEntityWrapper = new QueryWrapper<ServiceEntity>();
					serviceEntityWrapper.eq("ywfwbm", resourceCode);
					
					List<ServiceEntity> serviceEntityList = serviceService.list(serviceEntityWrapper);
										
					ServiceEntity serviceEntity = new ServiceEntity();
					

					if(serviceEntityList.size()==0){
						break;
					}
					else{
						serviceEntity=serviceEntityList.get(0);
					}
					
					// 查询对应的服务器最新指标项目对应的数值
					QueryWrapper<AppRunningEntity> appRunningWrapper = new QueryWrapper<AppRunningEntity>();
					appRunningWrapper.eq("app_code",resourceCode);
					appRunningWrapper.orderByDesc("create_time");
					List<AppRunningEntity> appRunningList = (List<AppRunningEntity>) appRunningService.list(appRunningWrapper);
					AppRunningEntity appRunningEntity = appRunningList.get(0);
					
					float targetValue = 0;
					
					if ("2".equals(strategyEntity.getTargetCode())) {//指标 为 内存使用率
						targetValue=Float.parseFloat(appRunningEntity.getMemUsePencent());
					}

					// 通过告警策略找到对应的阈值规则记录,把读取到的机器参数和阈值从高到底进行对比
					QueryWrapper<ThresholdGradeEntity> thresholdGradeEntityWrapper = new QueryWrapper<ThresholdGradeEntity>();
					thresholdGradeEntityWrapper.eq("strategy_id",strategyEntity.getId());
					thresholdGradeEntityWrapper.orderByDesc("threshold_level");
					List<ThresholdGradeEntity> thresholdGradeEntityList = (List<ThresholdGradeEntity>) thresholdGradeService.list(thresholdGradeEntityWrapper);

					// 阈值规则从高级到低级依次遍历
					for (int i = 0; i < thresholdGradeEntityList.size(); i++) {
						ThresholdGradeEntity thresholdGradeEntity = thresholdGradeEntityList.get(i);
						Float thresholdValue = Float.parseFloat(thresholdGradeEntity.getThreshold()); // 阈值
						String operation = thresholdGradeEntity.getCondtion(); // 对比条件
						String str = targetValue + operation + thresholdValue; // 对比表达式
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
						boolean operationResult = (boolean) engine.eval(str);
						// 如果operationResult为true,则说明触发了报警条件
						if (operationResult) {
							//按顺序从高到底依次查询是否有对应的未处理的告警记录,如果已经存在未处理的告警记录则不再生成告警记录
							QueryWrapper<RecordEntity> recordEntityWrapper = new QueryWrapper<RecordEntity>();
							recordEntityWrapper.eq("alarm_object", resourceCode);
							recordEntityWrapper.eq("alarm_record_type", "2");
							recordEntityWrapper.eq("alarm_grade", thresholdGradeEntity.getThresholdLevel());
							recordEntityWrapper.eq("alarm_target", strategyEntity.getTargetCode());

							
							List<RecordEntity> RecordEntityList = (List<RecordEntity>) recordService.list(recordEntityWrapper);
                            if(RecordEntityList.size()>0){
                            	break;
                            }
							// 生成告警记录
							RecordEntity recordEntity = new RecordEntity();

							recordEntity.setAlarmRecordType("2"); // 告警记录类型
							recordEntity.setAlarmObject(resourceCode); // 告警服务编码
							recordEntity.setAlarmTarget(strategyEntity.getTargetCode()); // 告警指标编码
							recordEntity.setAlarmValue(targetValue + ""); // 告警值
							recordEntity.setAlarmTime(new Date()); // 告警时间

							recordEntity.setPoliceType(serviceEntity.getYwjz()); // 服务器所属警种
							recordEntity.setHandleUserId(Long.parseLong(serviceEntity.getYwry() + "")); // 服务器运维人员

							recordEntity.setAlarmGrade(thresholdGradeEntity.getThresholdLevel()); // 告警级别
							recordEntity.setAlarmNoticeType(thresholdGradeEntity.getAlarmType()); // 告警通知方式
							recordEntity.setTimeLimit(thresholdGradeEntity.getRequirestHandleTime());//要求处理时间

							recordEntity.setHandleStatus("0");
							
							cn.com.bmsoft.utils.R result = serialNumberService.generator("YAPG");
							String recordCode = (String) result.get("serialNumber");
                            recordEntity.setAlarmRecordCode(recordCode);

							if (thresholdGradeEntity.getIsOrder() == 1) {
								InformationEntity informationEntity = new InformationEntity();
								String orderTitle = serviceEntity.getYwfwmc()
										+ "  发生  "
										+ strategyEntity.getTargetCode()
										+ "  告警 ";
								String orderContent = serviceEntity.getYwfwmc()
										+ "  发生 "
										+ strategyEntity.getTargetCode()
										+ "  告警,告警级别为:"
										+ thresholdGradeEntity.getThresholdLevel() 
										+ ",告警值为:"+ targetValue;

								informationEntity.setGdbt(orderTitle);
								informationEntity.setGdbsnr(orderContent);
								informationEntity.setGdssfwid(serviceEntity.getYwfwbm());
								informationEntity.setGdssfwmc(serviceEntity.getYwfwmc());
								informationEntity.setYxj("1");
								informationEntity.setGdlx("1");
								informationEntity.setGdzt("2");
								informationEntity.setSftj(1);
								informationEntity.setCjsj(new Date());

								//String orderCode = savaOrder(informationEntity); //生成工单,并返回工单ID
								//recordEntity.setOrderCode(orderCode);

							}
							if (thresholdGradeEntity.getAlarmType().equals("1")) {
								
								//String modelContent = createModelContent(thresholdGradeEntity.getEmailModeId(),recordEntity,serviceEntity.getYwfwmc());
								
								// 发送短信
							}
							if (thresholdGradeEntity.getAlarmType().equals("2")) {
								//String modelContent = createModelContent(thresholdGradeEntity.getMessageModelId(),recordEntity,serviceEntity.getYwfwmc());

								// 发送邮件
							}
							recordService.save(recordEntity);
						}

						if (operationResult) {
							break;
						}

					}

				}
		}
	}
	
	
    
	/**
     * 业务告警策略执行
     * @throws Exception
     */
	@GetMapping("/runSystemStrategy")
	public void runBusinessStrategy() throws Exception {
		
		QueryWrapper<AmStrategyEntity> amStrategyQueryWrapper = new QueryWrapper<AmStrategyEntity>();
		amStrategyQueryWrapper.eq("alarm_project_type", "2");
		List<AmStrategyEntity> strategyList = amStrategyService.list(amStrategyQueryWrapper);

		for (AmStrategyEntity strategyEntity : strategyList) {
		
				String[] resourceArr = strategyEntity.getResourceList().split(",");
				for (String resourceCode : resourceArr) {
					// 查询到对应的应用信息
					QueryWrapper<SystemEntity> systemEntityWrapper = new QueryWrapper<SystemEntity>();
					systemEntityWrapper.eq("ywfwbm", resourceCode);
					List<SystemEntity> systemEntityList = systemService.list(systemEntityWrapper);
					SystemEntity systemEntity = new SystemEntity();
                    if(systemEntityList.size()==0){
                    	break;
                    }
                    else{
                    	systemEntity=systemEntityList.get(0);
                    }

					// 查询对应的服务器最新指标项目对应的数值
					QueryWrapper<AppRunningEntity> appRunningWrapper = new QueryWrapper<AppRunningEntity>();
					appRunningWrapper.eq("app_code",resourceCode);
					appRunningWrapper.orderByDesc("create_time");
					List<AppRunningEntity> appRunningList = (List<AppRunningEntity>) appRunningService.list(appRunningWrapper);
					AppRunningEntity appRunningEntity = appRunningList.get(0);
					
					float targetValue = 0;
					
					if ("2".equals(strategyEntity.getTargetCode())) {//指标 为 内存使用率
						targetValue=Float.parseFloat(appRunningEntity.getMemUsePencent());
					}

					// 通过告警策略找到对应的阈值规则记录,把读取到的机器参数和阈值从高到底进行对比
					QueryWrapper<ThresholdGradeEntity> thresholdGradeEntityWrapper = new QueryWrapper<ThresholdGradeEntity>();
					thresholdGradeEntityWrapper.eq("strategy_id",strategyEntity.getId());
					thresholdGradeEntityWrapper.orderByDesc("threshold_level");
					List<ThresholdGradeEntity> thresholdGradeEntityList = (List<ThresholdGradeEntity>) thresholdGradeService.list(thresholdGradeEntityWrapper);

					// 阈值规则从高级到低级依次遍历
					for (int i = 0; i < thresholdGradeEntityList.size(); i++) {
						ThresholdGradeEntity thresholdGradeEntity = thresholdGradeEntityList.get(i);
						Float thresholdValue = Float.parseFloat(thresholdGradeEntity.getThreshold()); // 阈值
						String operation = thresholdGradeEntity.getCondtion(); // 对比条件
						String str = targetValue + operation + thresholdValue; // 对比表达式
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
						boolean operationResult = (boolean) engine.eval(str);
						// 如果operationResult为true,则说明触发了报警条件
						if (operationResult) {
							//按顺序从高到底依次查询是否有对应的未处理的告警记录,如果已经存在未处理的告警记录则不再生成告警记录
							QueryWrapper<RecordEntity> recordEntityWrapper = new QueryWrapper<RecordEntity>();
							recordEntityWrapper.eq("alarm_object", resourceCode);
							recordEntityWrapper.eq("alarm_record_type", "2");
							recordEntityWrapper.eq("alarm_grade", thresholdGradeEntity.getThresholdLevel());
							recordEntityWrapper.eq("alarm_target", strategyEntity.getTargetCode());

							
							List<RecordEntity> RecordEntityList = (List<RecordEntity>) recordService.list(recordEntityWrapper);
                            if(RecordEntityList.size()>0){
                            	break;
                            }
							// 生成告警记录
							RecordEntity recordEntity = new RecordEntity();

							recordEntity.setAlarmRecordType("2"); // 告警记录类型
							recordEntity.setAlarmObject(resourceCode); // 告警服务编码
							recordEntity.setAlarmTarget(strategyEntity.getTargetCode()); // 告警指标编码
							recordEntity.setAlarmValue(targetValue + ""); // 告警值
							recordEntity.setAlarmTime(new Date()); // 告警时间

							recordEntity.setPoliceType(systemEntity.getYwjz()); // 服务器所属警种
							recordEntity.setHandleUserId(Long.parseLong(systemEntity.getYwryid() + "")); // 服务器运维人员

							recordEntity.setAlarmGrade(thresholdGradeEntity.getThresholdLevel()); // 告警级别
							recordEntity.setAlarmNoticeType(thresholdGradeEntity.getAlarmType()); // 告警通知方式
							recordEntity.setTimeLimit(thresholdGradeEntity.getRequirestHandleTime());//要求处理时间

							recordEntity.setHandleStatus("0");
							
							cn.com.bmsoft.utils.R result = serialNumberService.generator("YAPG");
							String recordCode = (String) result.get("serialNumber");
                            recordEntity.setAlarmRecordCode(recordCode);

							if (thresholdGradeEntity.getIsOrder() == 1) {
								InformationEntity informationEntity = new InformationEntity();
								String orderTitle = systemEntity.getYwfwmc()
										+ "  发生  "
										+ strategyEntity.getTargetCode()
										+ "  告警 ";
								String orderContent = systemEntity.getYwfwmc()
										+ "  发生 "
										+ strategyEntity.getTargetCode()
										+ "  告警,告警级别为:"
										+ thresholdGradeEntity.getThresholdLevel() 
										+ ",告警值为:"+ targetValue;

								informationEntity.setGdbt(orderTitle);
								informationEntity.setGdbsnr(orderContent);
								informationEntity.setGdssfwid(systemEntity.getYwfwbm());
								informationEntity.setGdssfwmc(systemEntity.getYwfwmc());
								informationEntity.setYxj("1");
								informationEntity.setGdlx("1");
								informationEntity.setGdzt("2");
								informationEntity.setSftj(1);
								informationEntity.setCjsj(new Date());

								String orderCode = savaOrder(informationEntity,17); //生成工单,并返回工单ID
								recordEntity.setOrderCode(orderCode);

							}
							if (thresholdGradeEntity.getAlarmType().equals("1")) {
								
								//String modelContent = createModelContent(thresholdGradeEntity.getEmailModeId(),recordEntity,systemEntity.getYwfwmc());
								
								// 发送短信
							}
							if (thresholdGradeEntity.getAlarmType().equals("2")) {
								
								//String modelContent = createModelContent(thresholdGradeEntity.getMessageModelId(),recordEntity,systemEntity.getYwfwmc());

								// 发送邮件
							}
							recordService.save(recordEntity);
						}

						if (operationResult) {
							break;
						}

					}

				}
		}
	}
	
	

	
	public String savaOrder(InformationEntity information,int userId) {
		  cn.com.bmsoft.utils.R result = serialNumberService.generator("GD");
          String gdbh = (String) result.get("serialNumber");
          RestTemplate restTemplate = new RestTemplate();
          InformationEntity informationEntity = new InformationEntity();
          informationEntity.setGdbh("");

          informationEntity.setCjsj(new Date());

          String slid = "";//实例id
          String wjid = "";//环节id

          /*保存工单信息*/
          informationService.save(informationEntity);
          /*=========================生成工单后，启动工作流程===================================*/
          System.out.println("-------------------------------------rModelService="+rModelService);
          
          String modelId = rModelService.getById("1").getMbid();//指定绑定业务id，获取模板id
          HashMap<String, Object> map = new HashMap<String, Object>();
          map.put("modelId", modelId);
          map.put("modelId", modelId);
          /*生产工单时，流程启动工作流*/
          Map<String, Object> body = restTemplate.run(map);
          @SuppressWarnings("unchecked")
		  Map<String, Object> data = (Map<String, Object>) body.get("data");
          slid = (String) data.get("id");
          wjid = (String) data.get("taskId");
          //自定备份运维人员认领任务
          HashMap<String, Object> claim = new HashMap<>();
          claim.put("action", "claim");
          claim.put("assignee", userId);
          Map<String, Object> next = restTemplate.completeOrclaim(claim, wjid);//任务完成，工作流转到下一个节点
          /*工单过程表插入数据*/
          ProcessEntity processEntity = new ProcessEntity();
          processEntity.setGdbh(gdbh);
          processEntity.setGdhjid(wjid);
          processEntity.setGdzt("2");//1待处理
          processEntity.setCjsj(new Date());
          processService.save(processEntity);
          /*//工单信息表跟新插入实例id*/
          informationEntity.setSlid(slid);
          informationService.updateById(informationEntity);
          return gdbh;
	}
	
	public String createModelContent(int modelId,RecordEntity recodeEntity,String ObjectName){
		ModelEntity modelEntity = modelService.getById(modelId);
		String content = modelEntity.getModelContent();
		if("1".equals(recodeEntity.getAlarmRecordType())){
			content.replace("[资源名称]", ObjectName);
		}
		else{
			content.replace("[服务名称]",ObjectName);
		}
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		content.replace("[告警时间]", sDateFormat.format(new Date()));
		content.replace("[告警对象]", recodeEntity.getAlarmObject());
		content.replace("[告警指标]", recodeEntity.getAlarmTarget());
		content.replace("[告警值]", recodeEntity.getAlarmValue());
		content.replace("[告警等级]", recodeEntity.getAlarmGrade());
		content.replace("[网络类别]", recodeEntity.getNetworkType());
		content.replace("[业务警种]", recodeEntity.getPoliceType());
		content.replace("[IP地址]", recodeEntity.getIpAddress());
		return content;
		
	}
    
	

}
