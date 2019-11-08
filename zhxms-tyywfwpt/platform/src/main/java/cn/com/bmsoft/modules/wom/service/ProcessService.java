package cn.com.bmsoft.modules.wom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.wom.entity.ProcessEntity;

import java.util.List;
import java.util.Map;

/**
 * 工单管理-工单过程表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
public interface ProcessService extends IService<ProcessEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ProcessEntity> handleProcessInfo(int id);

    List<ProcessEntity> auditRecordsInfo(int id);

    List<ProcessEntity> transferredRecordsInfo(int id);

    ProcessEntity paifaInfo(int id);

    ProcessEntity infoBygdbh(String gdbh);
}

