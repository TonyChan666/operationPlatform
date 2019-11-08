package cn.com.bmsoft.modules.attachment.service;

import cn.com.bmsoft.modules.attachment.entity.AttachmentBusinessEntity;
import cn.com.bmsoft.modules.attachment.entity.AttachmentEntity;
import cn.com.bmsoft.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公共附件表 服务类
 * </p>
 *
 */
public interface AttachmentService extends IService<AttachmentEntity> {

   /**
    * 根据ID逻辑删除
    * @param id
    * @return
    * @throws Exception
    */
    int deleteByIdLogical(Serializable id) throws Exception;

    /**
     * 获取分页对象
     * @param
     * @return
     */
    IPage<AttachmentEntity> getAttachmentPageList(Map<String, Object> params);

    /**
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 列出附件业务关联信息
     * @param attachmentBusinessEntity
     * @return
     */
    List<AttachmentBusinessEntity> listAttachmentBusiness(AttachmentBusinessEntity attachmentBusinessEntity);

    /**
     * 插入附件业务关联信息
     * @param attachmentBusinessEntity
     * @return
     */
    int saveAttachmentBusiness(AttachmentBusinessEntity attachmentBusinessEntity);



}
