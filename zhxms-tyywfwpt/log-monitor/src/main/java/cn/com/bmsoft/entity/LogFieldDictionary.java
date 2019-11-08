package cn.com.bmsoft.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("log_field_dictionary")
public class LogFieldDictionary implements Serializable {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    //字段编码
    @TableField("field_code")
    private String fieldCode;
    //字段名称
    @TableField("field_name")
    private String fieldName;
    //字段描述
    @TableField("field_description")
    private String fieldDescription;
    //日志文件，列id
    @TableField("column_id")
    private int columnId;
    //表名
    @TableField("table_name")
    private String tableName;
}
