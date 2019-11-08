package cn.com.bmsoft.modules.attachment.service.impl;

import cn.com.bmsoft.modules.attachment.dao.AttachmentDao;
import cn.com.bmsoft.modules.attachment.entity.AttachmentBusinessEntity;
import cn.com.bmsoft.modules.attachment.entity.AttachmentEntity;
import cn.com.bmsoft.modules.attachment.service.AttachmentService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 公共附件表 服务实现类
 * </p>
 */
@Slf4j
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentDao, AttachmentEntity> implements AttachmentService {

    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public int deleteByIdLogical(Serializable id) throws Exception {
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setId(id.toString());
        attachmentEntity.setStatus("0");
        return attachmentDao.updateById(attachmentEntity);
    }

    @Override
    public IPage<AttachmentEntity> getAttachmentPageList(Map<String, Object> params) {
        return null;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttachmentEntity> page = this.page(
                new Query<AttachmentEntity>().getPage(params),
                new QueryWrapper<AttachmentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<AttachmentBusinessEntity> listAttachmentBusiness(AttachmentBusinessEntity attachmentBusinessEntity){
        return attachmentDao.selectAttachmentBusiness(attachmentBusinessEntity);
    }

    /**
     * 插入附件业务关联信息
     * @param attachmentBusinessEntity
     * @return
     */
    @Override
    public int saveAttachmentBusiness(AttachmentBusinessEntity attachmentBusinessEntity){
        return attachmentDao.insertAttachmentBusiness(attachmentBusinessEntity);
    }

}
