package cn.com.bmsoft.modules.am.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 短信记录表
 * 
 * @author zdh  zhengdaohuang@bmsoft.com.cn
 * @since 2019-10-09
 */
@Data
@TableName("am_message_record")
public class MessageRecordEntity implements Serializable {
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
	 * 接收人手机号码
	 */
	private String receiveNumber;
	/**
	 * 接收人姓名
	 */
	private String receiveName;
	/**
	 * 短信类型
	 */
	private String messageType;
	/**
	 * 短信内容
	 */
	private String messageConent;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private Long createUserId;
	/**
	 * 逻辑删除
	 */
	private Integer deleteFlag;

}
