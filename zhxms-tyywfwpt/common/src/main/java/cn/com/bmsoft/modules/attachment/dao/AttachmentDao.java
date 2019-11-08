package cn.com.bmsoft.modules.attachment.dao;

import cn.com.bmsoft.modules.attachment.entity.AttachmentBusinessEntity;
import cn.com.bmsoft.modules.attachment.entity.AttachmentEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AttachmentDao extends BaseMapper<AttachmentEntity> {

    /**
     * 列出附件业务关联信息
     * @param attachmentBusinessEntity
     * @return
     */
    List<AttachmentBusinessEntity> selectAttachmentBusiness(AttachmentBusinessEntity attachmentBusinessEntity);

    /**
     * 插入附件业务关联信息
     * @param attachmentBusinessEntity
     * @return
     */
    int insertAttachmentBusiness(AttachmentBusinessEntity attachmentBusinessEntity);

}
