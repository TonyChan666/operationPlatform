package cn.com.bmsoft.modules.rm.utils;

import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

public class SearchParamsUtils<T> {

    public QueryWrapper<T> mapParams2Wrapper(String[] searchFields,Map<String, Object> params, QueryWrapper<T> wrapper) {
        if(wrapper == null) {
            wrapper = new QueryWrapper<T>();
        }
        for (String key : searchFields) {
            if(params.get(key) != null && StringUtil.isNotEmpty(params.get(key)+"")){
                wrapper.like(key,params.remove(key));
            }
        }
        return wrapper;
    }

}
