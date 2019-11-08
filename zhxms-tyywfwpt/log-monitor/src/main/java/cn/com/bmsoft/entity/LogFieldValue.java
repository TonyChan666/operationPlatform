package cn.com.bmsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 日志记录字段值
 */
@Data
@TableName("log_field_value")
public class LogFieldValue implements Serializable {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    //记录id
    @TableField("record_id")
    private long recordId;
    //字段编码
    @TableField("field_code")
    private String fieldCode;
    //字段名
    @TableField("field_name")
    private String fieldName;
    //字段值
    @TableField("field_value")
    private String fieldValue;
    //表名
    @TableField("table_name")
    private String tableName;
}
