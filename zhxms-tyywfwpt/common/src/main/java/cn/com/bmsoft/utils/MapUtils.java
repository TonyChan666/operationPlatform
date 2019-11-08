package cn.com.bmsoft.utils;

import java.util.HashMap;


/**
 * Map工具类
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
