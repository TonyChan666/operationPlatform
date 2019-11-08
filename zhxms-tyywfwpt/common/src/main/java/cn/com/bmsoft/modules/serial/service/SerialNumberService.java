package cn.com.bmsoft.modules.serial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.R;
import cn.com.bmsoft.modules.serial.entity.SerialNumberEntity;

import java.util.List;
import java.util.Map;

/**
 * 公共流水号表
 *
 * @author luyuwei  luyuwei@bmsoft.com.cn
 * @since 2019-10-10
 */
public interface SerialNumberService extends IService<SerialNumberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 生成指定的序列号
     * @param code
     * @return
     */
    R generator(String code);

    List<SerialNumberEntity> getSerialNumberList();
}

