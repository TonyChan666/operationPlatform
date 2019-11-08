package cn.com.bmsoft.modules.sys.dao;

import cn.com.bmsoft.modules.sys.entity.SysCaptchaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
