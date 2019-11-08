package cn.com.bmsoft.modules.wom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.bmsoft.utils.PageUtils;
import cn.com.bmsoft.modules.wom.entity.InformationEntity;

import java.util.Collection;
import java.util.Map;

/**
 * 工单管理-工单信息表
 *
 * @author sufang  sufang@bmsoft.com.cn
 * @since 2019-09-25
 */
public interface InformationService extends IService<InformationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getDaiChuLiList(Map<String, Object> params,Collection<String> list);


    PageUtils getDaiBanPageList(Map<String, Object> params);

    PageUtils getDaiBanLiList(Map<String, Object> params);

   // PageUtils getdaiShengHePageList(Map<String, Object> params, Collection<String> list);

    PageUtils getDaiPaiFaPageList(Map<String, Object> params, Collection<String> list);

    PageUtils getDaiBanJiePageList(Map<String, Object> params);

    PageUtils getShenQingPageList(Map<String, Object> params, Long user);

    void deleteByIds(Long[] ids);

    InformationEntity getInformationById(int id);

    PageUtils getdaiShengHePageList(Map<String, Object> params);

    PageUtils getDaiPaiFaPageList(Map<String, Object> params);

    PageUtils warnList(Map<String, Object> params);

    PageUtils home(Map<String, Object> params);
}

