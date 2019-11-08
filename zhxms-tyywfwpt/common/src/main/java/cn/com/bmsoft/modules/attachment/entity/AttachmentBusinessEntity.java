package cn.com.bmsoft.modules.attachment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 公共附件业务关联表
 */
@Data
@TableName("c_attachment_business")
public class AttachmentBusinessEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 业务表名
     */
    private String businessTableName;
    /**
     * 业务表ID
     */
    private String businessTableId;
    /**
     * 附件表ID
     */
    private String attachmentId;
    /**
     * 附件名
     */
    private String attachmentName;

}
