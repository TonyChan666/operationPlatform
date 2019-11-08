package cn.com.bmsoft.modules.wom.dao;

import cn.com.bmsoft.modules.wom.entity.ProcessEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工单管理-工单过程表
 * 
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
@Mapper
public interface ProcessDao extends BaseMapper<ProcessEntity> {

    List<ProcessEntity> transferredRecordsInfo(int id);

    List<ProcessEntity> auditRecordsInfo(int id);

    List<ProcessEntity> handleProcessInfo(int id);

    ProcessEntity paifaInfo(int id);

    ProcessEntity infoBygdbh(String gdbh);
}
