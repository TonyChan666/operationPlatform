package cn.com.bmsoft.modules.rm.utils;

import java.util.HashMap;
import java.util.Map;

public class RmParams {

    /**
     * 各个资源编码前缀<key是entityName, value是编码的前缀>
     */
    public final static Map<String, String> CODE_PREFIX = new HashMap<String, String>();
    /**
     * 各个资源表名称<key是entityName, value是表的名称>
     */
    public final static Map<String, String> RM_TABLE_NAME = new HashMap<String, String>();
    /**
     * 各个资源表中文名<key是表的名称, value是表的中文名>
     */
    public final static Map<String, String> RM_TABLE_NAME_C = new HashMap<String, String>();

    /**
     * 各个资源表的entityName
     */
    public final static String SERVER_ENTITY_NAME = "server";
    public final static String COMPONENT_ENTITY_NAME = "component";
    public final static String LINK_ENTITY_NAME = "link";
    public final static String CONTAINER_ENTITY_NAME = "container";
    public final static String STORAGE_ENTITY_NAME = "storage";
    public final static String NETWORKDEVICE_ENTITY_NAME = "networkDevice";
    public final static String BSM_SERVICE_NAME = "service";
    public final static String BSM_SYSTEM_NAME = "system";

    /**
     * 自增编号接口返回map中编号的key
     */
    public final static String SERIAL_NUMBER = "serialNumber";

    public final static String FIELD_OVERMAX_TIPS = "字段值长度过长，请不要超过";
    public final static String FIELD_OVERMAX_TIPS2 = "个字符";

    static {
        //服务器
        CODE_PREFIX.put(SERVER_ENTITY_NAME,"FWQ");
        RM_TABLE_NAME.put(SERVER_ENTITY_NAME,"rm_server");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(SERVER_ENTITY_NAME),"服务器");
        //组件
        CODE_PREFIX.put(COMPONENT_ENTITY_NAME,"ZJ");
        RM_TABLE_NAME.put(COMPONENT_ENTITY_NAME,"rm_component");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(COMPONENT_ENTITY_NAME),"组件");
        //链路
        CODE_PREFIX.put(LINK_ENTITY_NAME,"LINE");
        RM_TABLE_NAME.put(LINK_ENTITY_NAME,"rm_link");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(LINK_ENTITY_NAME),"链路");
        //容器
        CODE_PREFIX.put(CONTAINER_ENTITY_NAME,"RQ");
        RM_TABLE_NAME.put(CONTAINER_ENTITY_NAME,"rm_container");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(CONTAINER_ENTITY_NAME),"容器");
        //存储资源
        CODE_PREFIX.put(STORAGE_ENTITY_NAME,"CC");
        RM_TABLE_NAME.put(STORAGE_ENTITY_NAME,"rm_storage");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(STORAGE_ENTITY_NAME),"存储资源");
        //网络设备
        CODE_PREFIX.put(NETWORKDEVICE_ENTITY_NAME,"WSSB");
        RM_TABLE_NAME.put(NETWORKDEVICE_ENTITY_NAME,"rm_network_device");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(NETWORKDEVICE_ENTITY_NAME),"网络设备");
        //业务管理：服务管理
        CODE_PREFIX.put(BSM_SERVICE_NAME,"FWGL");
        RM_TABLE_NAME.put(BSM_SERVICE_NAME,"bsm_service");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(BSM_SERVICE_NAME),"服务管理");
        //业务管理：业务系统
        CODE_PREFIX.put(BSM_SYSTEM_NAME,"YWXT");
        RM_TABLE_NAME.put(BSM_SYSTEM_NAME,"bsm_system");
        RM_TABLE_NAME_C.put(RM_TABLE_NAME.get(BSM_SYSTEM_NAME),"业务系统");
    }
}
