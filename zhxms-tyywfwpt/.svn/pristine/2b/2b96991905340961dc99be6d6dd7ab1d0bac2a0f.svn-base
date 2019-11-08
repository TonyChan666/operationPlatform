package cn.com.bmsoft.modules.am.service.impl;

import cn.com.bmsoft.modules.am.entity.ThresholdGradeEntity;
import cn.com.bmsoft.modules.am.service.ThresholdGradeService;
import cn.com.bmsoft.modules.am.utils.AmParams;
import cn.com.bmsoft.modules.am.vo.AmStrategyVo;
import cn.com.bmsoft.modules.dictionary.entity.DictionaryItemEntity;
import cn.com.bmsoft.modules.dictionary.service.DictionaryItemService;
import cn.com.bmsoft.utils.SpringContextUtils;
import cn.com.bmsoft.utils.StringUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.AmStrategyDao;
import cn.com.bmsoft.modules.am.entity.AmStrategyEntity;
import cn.com.bmsoft.modules.am.service.AmStrategyService;
import org.springframework.transaction.annotation.Transactional;


@Service("amStrategyService")
public class AmStrategyServiceImpl extends ServiceImpl<AmStrategyDao, AmStrategyEntity> implements AmStrategyService {

    @Autowired
    private ThresholdGradeService thresholdGradeService;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AmStrategyEntity> amStrategyEntityQueryWrapper = new QueryWrapper<AmStrategyEntity>();
        String resourceName = (String)params.get("resourceName");
        String alarmStrategyName = (String)params.get("alarmStrategyName");
        String targetCode = (String)params.get("targetCode");
        String isActive = (String)params.get("isActive");
        String resourceType = (String)params.get("resourceType");
        String alarmProjectType = (String)params.get("alarmProjectType");
        IPage<AmStrategyEntity> page = this.page(
                new Query<AmStrategyEntity>().getPage(params),
                amStrategyEntityQueryWrapper
                .like(StringUtil.isNotBlank(alarmStrategyName), "alarm_strategy_name", alarmStrategyName)
                .like(StringUtil.isNotBlank(targetCode), "target_code", targetCode)
                .like(StringUtil.isNotBlank(resourceName), "resource_name", resourceName)
                .eq(StringUtil.isNotBlank(isActive), "is_active", isActive)
                .eq(StringUtil.isNotBlank(resourceType), "resource_type", resourceType)
                .eq(StringUtil.isNotBlank(alarmProjectType), "alarm_project_type", alarmProjectType)
                .isNull("delete_flag").orderByDesc("create_time")
        );
        //设置字典值
        page.getRecords().forEach(amStrategyEntity -> {
            amStrategyEntity.setResourceType(AmParams.TABLE_NAME.get(amStrategyEntity.getResourceType()));
            DictionaryItemEntity item = dictionaryItemService.getOne(new QueryWrapper<DictionaryItemEntity>().eq("dict_code", "gjzb")
                    .eq("value", amStrategyEntity.getTargetCode()));
            amStrategyEntity.setTargetCode(item != null ? item.getName(): null);
        });
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveAmStrategyEntity(AmStrategyVo strategy) {
        strategy.setId(Integer.valueOf(new SimpleDateFormat("yyMMdd").format(new Date())+((int)(Math.random() * 1000))));
        setResourceName(strategy);
        AmStrategyEntity amStrategyEntity = VoToEntity(strategy);
        this.save(amStrategyEntity);
        //保存阈值
        strategy.getThresholdGradeEntities().forEach(thresholdGradeEntity -> {
            if (StringUtil.isNotBlank(thresholdGradeEntity.getThreshold())) {
                try {
                    thresholdGradeEntity.setStrategyId(strategy.getId());
                    thresholdGradeEntity.setCreateTime(strategy.getCreateTime());
                    thresholdGradeEntity.setCreateUserId(strategy.getCreateUserId());
                    //特殊字符转码
                    thresholdGradeEntity.setCondtion(URLDecoder.decode(thresholdGradeEntity.getCondtion(), "UTF-8"));
                    thresholdGradeService.save(thresholdGradeEntity);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    @Transactional
    public void updateAmStrategyById(AmStrategyVo strategy) {
        if (strategy.getThresholdGradeEntities() != null && strategy.getThresholdGradeEntities().size()>0) {
            //将id字符串，转为对应的名称字符串
            setResourceName(strategy);
            //逻辑删除阈值
            ThresholdGradeEntity updateThresholdGradeEntity = new ThresholdGradeEntity();
            updateThresholdGradeEntity.setStrategyId(strategy.getId());
            updateThresholdGradeEntity.setDeleteFlag(0);
            thresholdGradeService.update(updateThresholdGradeEntity,
                    new QueryWrapper<ThresholdGradeEntity>().eq("strategy_id", strategy.getId()));
            //保存阈值
            strategy.getThresholdGradeEntities().forEach(thresholdGradeEntity -> {
                if (thresholdGradeEntity.getThreshold() != null) {
                    try {
                        thresholdGradeEntity.setStrategyId(strategy.getId());
                        thresholdGradeEntity.setCreateTime(strategy.getCreateTime());
                        thresholdGradeEntity.setCreateUserId(strategy.getCreateUserId());
                        thresholdGradeEntity.setUpdateTime(strategy.getUpdateTime());
                        thresholdGradeEntity.setUpdateUserId(strategy.getUpdateUserId());
                        //特殊字符转码
                        thresholdGradeEntity.setCondtion(URLDecoder.decode(thresholdGradeEntity.getCondtion(), "UTF-8"));
                        thresholdGradeService.save(thresholdGradeEntity);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        AmStrategyEntity amStrategyEntity = VoToEntity(strategy);
        this.updateById(amStrategyEntity);
    }

    @Override
    @Transactional
    public void removeStrategyByIds(List<Long> asList) {
        asList.forEach(id -> {
            ThresholdGradeEntity updateThresholdGradeEntity = new ThresholdGradeEntity();
            AmStrategyEntity amStrategyEntity = new AmStrategyEntity();
            updateThresholdGradeEntity.setStrategyId(Math.toIntExact(id));
            amStrategyEntity.setId(Math.toIntExact(id));
            amStrategyEntity.setDeleteFlag(0);
            updateThresholdGradeEntity.setDeleteFlag(0);
            thresholdGradeService.update(updateThresholdGradeEntity,
                    new QueryWrapper<ThresholdGradeEntity>().eq("strategy_id", id));
            this.updateById(amStrategyEntity);
        });
    }

    @Override
    public PageUtils queryItemPage(Map<String, Object> params) {
        String resourceType = (String)params.get("resourceType");
        String itemName = (String)params.get("itemName");
        String exceptListString = (String) params.get("exceptList");
        List exceptList = exceptListString == null?null:Arrays.asList(exceptListString.split(","));
        String status;
        //利用反射获取service
        if (resourceType.equals("bsm_service") || resourceType.equals("bsm_system")) {
            status = "status";
        }else {
            status = "sfyx";
        }
        IService bean  = (IService) SpringContextUtils.getBean(AmParams.CODE_PREFIX .get(resourceType));
        String name  = AmParams.RM_TABLE_NAME.get(resourceType);
        IPage page = bean.pageMaps(
                new Query<>().getPage(params),
                new QueryWrapper<>().select("id as `id`", name + " as `name`")
                        .like(StringUtil.isNotBlank(itemName), name, itemName)
                        .notIn(exceptList!=null&&exceptList.size()>0, "id", exceptList)
                        .eq(status, 1)
                        .isNull("delete_flag"));
        return new PageUtils(page);
    }

    /**
     * Vo转换为entity
     *
     * @param vo
     * @return
     */
    private AmStrategyEntity VoToEntity(AmStrategyVo vo) {
        AmStrategyEntity amStrategyEntity = new AmStrategyEntity();
        BeanCopier copier = BeanCopier.create(AmStrategyVo.class, AmStrategyEntity.class, false);
        copier.copy(vo, amStrategyEntity, null);
        return amStrategyEntity;
    }
    
     /**   
      * @Author:         zdh on 2019/11/3 11:06
      * @param:          
      * @return:         
      * @Description: 设置资源名称
      *
      * @param strategy
      */
     public void setResourceName(AmStrategyVo strategy){
         String resourceType = strategy.getResourceType();
        // String businessType = strategy.getBusinessType();
         List<String> list;
         //获取id字符串
         String[] resourceList = strategy.getResourceList().split(",");
         IService bean = (IService) SpringContextUtils.getBean(AmParams.CODE_PREFIX .get(resourceType));
         //获取名称集合
         list = bean.listObjs(new QueryWrapper<>().select(AmParams.RM_TABLE_NAME.get(resourceType))
                 .in("id", Arrays.asList(resourceList)));
         //组合名称字符串以“，”相隔
         String resourceName = String.join(",", list);
         strategy.setResourceName(resourceName);
     }

}