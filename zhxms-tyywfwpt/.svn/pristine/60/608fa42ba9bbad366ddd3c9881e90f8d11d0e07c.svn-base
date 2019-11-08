package cn.com.bmsoft.modules.serial.service.impl;

import cn.com.bmsoft.modules.serial.dao.SerialNumberDao;
import cn.com.bmsoft.modules.serial.entity.SerialNumberEntity;
import cn.com.bmsoft.modules.serial.service.SerialNumberService;
import cn.com.bmsoft.modules.serial.utils.SerialNumberSingleton;
import cn.com.bmsoft.utils.DateUtils;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;
import cn.com.bmsoft.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("serialNumberService")
public class SerialNumberServiceImpl extends ServiceImpl<SerialNumberDao, SerialNumberEntity> implements SerialNumberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerialNumberEntity> page = this.page(
                new Query<SerialNumberEntity>().getPage(params),
                new QueryWrapper<SerialNumberEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 暂时只支持简单的编码配置规则：
     * 1、prefix+X位流水号
     * 2、prefix+日期+X位流水号
     */
    @Override
    public R generator(String code){

        //查找符合编码前缀的规则
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("code", code);
        String systemCode = "";
        synchronized (SerialNumberSingleton.getSerialNumberInstance().getSerialNumberMap().get(code)){
            //初始化变量
//            SerialNumberEntity serialNumberEntity = SerialNumberSingleton.getSerialNumberInstance().getSerialNumberMap().get(code);
            SerialNumberEntity serialNumberEntity = this.getOne(new QueryWrapper<SerialNumberEntity>().eq("code",code));
            String systemCodeTemplate = null;

            if(null == serialNumberEntity) {
               return R.error(500, "未成功生成新编码序列，请联系管理员。");
            }

            if(null != serialNumberEntity ){
                String rule = serialNumberEntity.getRule();
                String formatCode = serialNumberEntity.getFormatCode();
                Integer nextValue = serialNumberEntity.getNextValue();
                Integer increaseValue = serialNumberEntity.getIncreaseValue();

                String[] rules = rule.split("\\+");
                String dateString = rules[1];
                String nextSerialNumber = String.format(formatCode, nextValue);

                if (!"yyyyMMdd".equals(dateString)) {
                    systemCodeTemplate = "{0}{1}"; // prefix + 流水号
                    systemCode = MessageFormat.format(systemCodeTemplate, code, nextSerialNumber);
                } else {
                    systemCodeTemplate = "{0}{1}{2}"; // prefix + 日期 + 流水号
                    systemCode = MessageFormat.format(systemCodeTemplate, code, DateUtils.format(new Date(), "yyyyMMdd"), nextSerialNumber);
                }
                //更新下一序列号
                serialNumberEntity.setNextValue(nextValue + increaseValue);
                SerialNumberSingleton.getSerialNumberInstance().getSerialNumberMap().put(code,serialNumberEntity);
                int count = this.baseMapper.updateById(serialNumberEntity);
                if(count == 0) {
                    //说明这个编码在字典中已经被删除掉了，需要同时remove掉map中的
                    SerialNumberSingleton.getSerialNumberInstance().getSerialNumberMap().remove(code);
                    return R.error(500, "未成功生成新编码序列，请联系管理员。");
                }
            }else{
                return R.error(500, "未成功生成新编码序列，请联系管理员。");
            }
        }
        return R.ok().put("serialNumber", systemCode);
    }


    @Override
    public List<SerialNumberEntity> getSerialNumberList() {
        return this.baseMapper.selectByMap(new HashMap<>());
    }

}