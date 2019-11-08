package cn.com.bmsoft.modules.serial.utils;

import cn.com.bmsoft.modules.serial.entity.SerialNumberEntity;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.utils.SpringContextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerialNumberSingleton {

    private SerialNumberSingleton(){}

    private static SerialNumberSingleton serialNumberInstance = new SerialNumberSingleton();

    private static Map<String, SerialNumberEntity> serialNumberMap = new HashMap<>();

    static{
        SerialNumberService serialNumberService = SpringContextUtils.getBean("serialNumberService", SerialNumberService.class);
        List<SerialNumberEntity> serialNumberList = serialNumberService.getSerialNumberList();
        for(SerialNumberEntity serialNumberEntity : serialNumberList) {
            serialNumberMap.put(serialNumberEntity.getCode(), serialNumberEntity);
        }
    }

    public Map<String, SerialNumberEntity> getSerialNumberMap() {
        return serialNumberMap;
    }

    public static SerialNumberSingleton getSerialNumberInstance() {
        return serialNumberInstance;
    }


}
