package cn.com.bmsoft.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author luyuwei luyuwei@bmsoft.com.cn
 */
@GroupSequence({AddGroup.class, cn.com.bmsoft.validator.group.UpdateGroup.class})
public interface Group {

}
