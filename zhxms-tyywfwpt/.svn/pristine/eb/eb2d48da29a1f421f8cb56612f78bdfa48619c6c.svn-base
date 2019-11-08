package cn.com.bmsoft.modules.sa.controller;

import cn.com.bmsoft.modules.sa.service.SaWomService;
import cn.com.bmsoft.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 工单统计
 *
 * @author luyuwei
 */
@RestController
@RequestMapping("/sa/wom")
public class SaWomController {

    @Autowired
    SaWomService saWomService;

    /**
     * 工单数量趋势统计（可按日、周、月来统计）
     * @param queryMap
     * @return
     */
    @RequestMapping("/quantityTrendOfWorkOrder")
    public R quantityTrendOfWorkOrder(@RequestParam Map<String, Object> queryMap){
        String type = (String)queryMap.get("type");
        if(null == type){
            return R.error(500,"参数值[type]不能为空！");
        }
        String format = "";
        switch(type) {
            case "day":
                format = "MM-dd";
                break;
            case "week":
                format = "D";
                break;
            case "month":
                format = "MM";
                break;
            default:
                return R.error(500,"参数值[type]不正确！");
        }
        queryMap.put("format", format);
        List<Map<String, Object>> result = saWomService.quantityTrendOfWorkOrder(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 上月工单数统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/workOrderOfLastMonth")
    public R workOrderOfLastMonth(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saWomService.workOrderOfLastMonth(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 上月工单解决率统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/workOrderResolutionRateOfLastMonth")
    public R workOrderResolutionRateOfLastMonth(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saWomService.workOrderResolutionRateOfLastMonth(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 工单类型占比统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/proportionOfWorkOrderType")
    public R proportionOfWorkOrderType(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saWomService.proportionOfWorkOrderType(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 排名前十的工单创建人统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/workOrderCreaterOfTopTen")
    public R workOrderCreaterOfTopTen(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saWomService.workOrderCreaterOfTopTen(queryMap);
        return R.ok().put("data", result);
    }

    @RequestMapping("/workOrder")
    public R workOrder(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saWomService.workOrder(queryMap);
        return R.ok().put("data", result);
    }
}
