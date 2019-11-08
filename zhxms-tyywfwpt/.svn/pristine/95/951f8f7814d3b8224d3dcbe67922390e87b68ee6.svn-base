package cn.com.bmsoft.modules.attachment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 公共附件表
 * </p>
 *
 * @author diaohancai
 * @since 2019-08-26
 */
@Data
@TableName("c_attachment")
public class AttachmentEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 存储文件名
     */
    private String storageFileName;
    /**
     * 原始文件名
     */
    private String originalFileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小 KB
     */
    private Integer fileSize;
    /**
     * 相对路径
     */
    private String relativePath;
    /**
     * 绝对路径
     */
    private String absolutePath;
    /**
     * 创建人ID
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人ID
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 是否有效
     */
    private String status;

}
