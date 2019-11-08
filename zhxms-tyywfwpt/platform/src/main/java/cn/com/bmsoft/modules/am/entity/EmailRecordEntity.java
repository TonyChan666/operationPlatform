package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 邮件记录表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_email_record")
public class EmailRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 接收人邮件地址
	 */
	private String receiveAddress;
	/**
	 * 接收人姓名
	 */
	private String receiveName;
	/**
	 * 邮件类型
	 */
	private String emailType;
	/**
	 * 邮件标题
	 */
	private String emailTitle;
	/**
	 * 邮件内容
	 */
	private String emailContent;
	/**
	 * 创建人
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;

}
