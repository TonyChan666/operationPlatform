package cn.com.bmsoft.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Rio机构Dto
 */
@Data
public class RioOrganDto implements Serializable {
    //机构 ID
    private String id;
    //机构名称
    private String name;
    //机构标识
    private String badge;
    //机构描述
    private String description;
    //负责人成员 ID
    private String[] managers;
}
