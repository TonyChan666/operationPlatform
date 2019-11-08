package cn.com.bmsoft.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * Rio负责人Rio
 */
@Data
public class RioOperatorDto implements Serializable {
    //负责人帐号 ID
    @JSONField(name = "account_id")
    private String accountId;
    //负责人帐号名
    @JSONField(name = "account_name")
    private String accountName;
}
