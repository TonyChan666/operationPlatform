package cn.com.bmsoft.modules.am.service.impl;

import cn.com.bmsoft.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.utils.Query;

import cn.com.bmsoft.modules.am.dao.ModelDao;
import cn.com.bmsoft.modules.am.entity.ModelEntity;
import cn.com.bmsoft.modules.am.service.ModelService;
import org.springframework.transaction.annotation.Transactional;


@Service("modelService")
public class ModelServiceImpl extends ServiceImpl<ModelDao, ModelEntity> implements ModelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ModelEntity> page = this.page(
                new Query<ModelEntity>().getPage(params),
                new QueryWrapper<ModelEntity>()
                        .select("id",
                                "send_type_code",
                                "send_type_name",
                                "send_channel",
                                "is_active",
                                "(select t.name from c_dictionary_item t where t.dict_code='gjfs' and t.value=am_model.send_channel) send_channel_name")
                        .like(StringUtil.isNotBlank((String)params.get("sendTypeName")), "send_type_name", params.get("sendTypeName"))
                        .eq(StringUtil.isNotBlank((String)params.get("sendChannel")), "send_channel", params.get("sendChannel"))
                        .eq(StringUtil.isNotBlank((String)params.get("isActive")), "is_active", params.get("isActive"))
                        .isNull("delete_flag")
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void removeModelEntityByIds(List<Long> asList) {
        asList.forEach(id -> {
            ModelEntity modelEntity = new ModelEntity();
            modelEntity.setId(Math.toIntExact(id));
            modelEntity.setDeleteFlag(0);
            this.updateById(modelEntity);
        });
    }

}