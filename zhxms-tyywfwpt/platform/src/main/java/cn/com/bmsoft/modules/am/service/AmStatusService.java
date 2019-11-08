package cn.com.bmsoft.modules.am.service;

import cn.com.bmsoft.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface AmStatusService {

    Integer link();

    List<Map<String, Object>> intranet();

    PageUtils amRecord(Map<String, Object> params);
}
