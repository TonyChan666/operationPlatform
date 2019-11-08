package cn.com.bmsoft.config;

import cn.com.bmsoft.modules.sys.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yuwei
 * @since September 12,2019
 *
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Component
public class MetaHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        /*this.setFieldValByName("sfyx","1", metaObject);
        if(null != SecurityUtils.getSubject().getPrincipal()){
            Long userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
            this.setFieldValByName("cjr", String.valueOf(userId), metaObject);
            this.setFieldValByName("cjsj", new Date(), metaObject);
            this.setFieldValByName("zhxgr", String.valueOf(userId), metaObject);
            this.setFieldValByName("zhxgsj", new Date(), metaObject);

            this.setFieldValByName("createUserId", userId, metaObject);
            this.setFieldValByName("createTime", new Date(), metaObject);
            this.setFieldValByName("updateUserId", userId, metaObject);
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }*/

    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        /*if(null != SecurityUtils.getSubject().getPrincipal()){
            Long userId = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUserId();
            this.setFieldValByName("zhxgr", String.valueOf(userId), metaObject);
            this.setFieldValByName("zhxgsj", new Date(), metaObject);
        }*/
    }

}