package cn.com.bmsoft.modules.am.utils;

import cn.com.bmsoft.modules.rm.service.*;
import cn.com.bmsoft.modules.rm.service.impl.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

public class AmParams {

    /**
     * 各个编码对应service依赖
     */
    public final static Map<String, String> CODE_PREFIX = new HashMap<String, String>();
    /**
     * 各个实体对应的名称字段
     */
    public final static Map<String, String> RM_TABLE_NAME = new HashMap<String, String>();
    /**
     * 各个实体对应的编码字段
     */
    public final static Map<String, String> RM_TABLE_CODE = new HashMap<String, String>();
    /**
     * 各个实体对应的表名
     */
    public final static Map<String, String> TABLE_NAME = new HashMap<String, String>();

    public final static String SERVER_ENTITY_SERVICE = "rm_server";
    public final static String NETWORKDEVICE_ENTITY_SERVICE = "rm_network_device";
    public final static String LINK_ENTITY_SERVICE = "rm_link";
    public final static String CONTAINER_ENTITY_SERVICE = "rm_container";
    public final static String STORAGE_ENTITY_SERVICE = "rm_storage";
    public final static String COMPONENT_ENTITY_SERVICE = "rm_component";
    public final static String PROBE_ENTITY_SERVICE = "rm_probe";
    public final static String BUSINESS_SERVICE = "bsm_service";
    public final static String BUSINESS_SYSTEM = "bsm_system";
    static {
        //服务器
        CODE_PREFIX.put(SERVER_ENTITY_SERVICE,"serverService");
        RM_TABLE_NAME.put(SERVER_ENTITY_SERVICE, "SBMC");
        RM_TABLE_CODE.put(SERVER_ENTITY_SERVICE, "FWQMC");
        TABLE_NAME.put(SERVER_ENTITY_SERVICE,"服务器");
        //网络设备
        CODE_PREFIX.put(NETWORKDEVICE_ENTITY_SERVICE,"networkDeviceService");
        RM_TABLE_NAME.put(NETWORKDEVICE_ENTITY_SERVICE, "SBMC");
        RM_TABLE_CODE.put(NETWORKDEVICE_ENTITY_SERVICE, "SBBH");
        TABLE_NAME.put(NETWORKDEVICE_ENTITY_SERVICE,"网络设备");
        //链路
        CODE_PREFIX.put(LINK_ENTITY_SERVICE,"linkService");
        RM_TABLE_NAME.put(LINK_ENTITY_SERVICE, "LLMC");
        RM_TABLE_CODE.put(LINK_ENTITY_SERVICE, "LLBM");
        TABLE_NAME.put(LINK_ENTITY_SERVICE,"链路");
        //容器
        CODE_PREFIX.put(CONTAINER_ENTITY_SERVICE,"containerService");
        RM_TABLE_NAME.put(CONTAINER_ENTITY_SERVICE, "RQMC");
        RM_TABLE_CODE.put(COMPONENT_ENTITY_SERVICE, "RQBM");
        TABLE_NAME.put(COMPONENT_ENTITY_SERVICE,"容器");
        //存储资源
        CODE_PREFIX.put(STORAGE_ENTITY_SERVICE,"storageService");
        RM_TABLE_NAME.put(STORAGE_ENTITY_SERVICE, "CCMC");
        RM_TABLE_CODE.put(STORAGE_ENTITY_SERVICE, "CCBM");
        TABLE_NAME.put(STORAGE_ENTITY_SERVICE,"存储资源");
        //组件
        CODE_PREFIX.put(COMPONENT_ENTITY_SERVICE,"componentService");
        RM_TABLE_NAME.put(COMPONENT_ENTITY_SERVICE, "ZJMC");
        RM_TABLE_CODE.put(COMPONENT_ENTITY_SERVICE, "ZJBH");
        TABLE_NAME.put(COMPONENT_ENTITY_SERVICE,"组件");
        //探针
        CODE_PREFIX.put(PROBE_ENTITY_SERVICE,"probeService");
        RM_TABLE_NAME.put(PROBE_ENTITY_SERVICE, "TZYYMC");
        RM_TABLE_CODE.put(PROBE_ENTITY_SERVICE, "TZBH");
        TABLE_NAME.put(PROBE_ENTITY_SERVICE,"探针");
        //业务服务
        CODE_PREFIX.put(BUSINESS_SERVICE,"serviceService");
        RM_TABLE_NAME.put(BUSINESS_SERVICE, "YWFWMC");
        RM_TABLE_CODE.put(BUSINESS_SERVICE, "YWFWBM");
        TABLE_NAME.put(BUSINESS_SERVICE,"业务服务");

        //业务系统
        CODE_PREFIX.put(BUSINESS_SYSTEM,"systemService");
        RM_TABLE_NAME.put(BUSINESS_SYSTEM, "YWFWMC");
        RM_TABLE_CODE.put(BUSINESS_SYSTEM, "YWFWBM");
        TABLE_NAME.put(BUSINESS_SYSTEM,"业务系统");
    }
}
