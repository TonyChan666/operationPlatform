package cn.com.bmsoft.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 组织机构表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-09-26
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    List<SysDeptEntity> listByAll(Integer deptId);
}

