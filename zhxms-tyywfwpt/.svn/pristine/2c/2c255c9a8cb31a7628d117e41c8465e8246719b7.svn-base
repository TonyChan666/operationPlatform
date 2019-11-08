package cn.com.bmsoft.modules.sa.controller;

import cn.com.bmsoft.modules.sa.service.SaFaultService;
import cn.com.bmsoft.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sa/fault")
public class SaFaultController {

    @Autowired
    SaFaultService saFaultService;

    /**
     * 故障数量趋势统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/quantityTrendOfFault")
    public R quantityTrendOfFault(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saFaultService.quantityTrendOfFault(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 排名前十的故障统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/faultOfTopTen")
    public R faultOfTopTen(@RequestParam Map<String, Object> queryMap){
        String type = (String)queryMap.get("type");
        if(null == type){
            return R.error(500,"参数值[type]不能为空！");
        }
        List<Map<String, Object>> result = saFaultService.faultOfTopTen(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 故障数据分析，包括当月资源故障数，当月服务故障数
     */
    @RequestMapping("/alarmCount")
    public R alarmCount(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saFaultService.alarmCount(queryMap);
        return R.ok().put("data", result);
    }

}
