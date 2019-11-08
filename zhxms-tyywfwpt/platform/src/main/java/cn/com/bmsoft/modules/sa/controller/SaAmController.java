package cn.com.bmsoft.modules.sa.controller;
import cn.com.bmsoft.modules.sa.service.SaAmService;
import cn.com.bmsoft.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sa/am")
public class SaAmController {

    @Autowired
    SaAmService saAmService;

    /**
     * 告警数量趋势统计（可按日、周、月来统计）
     * @param queryMap
     * @return
     */
    @RequestMapping("/quantityTrendOfAlarm")
    public R quantityTrendOfAlarm(@RequestParam Map<String, Object> queryMap){
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
        List<Map<String, Object>> result = saAmService.quantityTrendOfAlarm(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 告警类型占比统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/proportionOfAlarmType")
    public R proportionOfAlarmType(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saAmService.proportionOfAlarmType(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 排名前十的告警对象统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/alarmObjectOfTopTen")
    public R alarmObjectOfTopTen(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saAmService.alarmObjectOfTopTen(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 排名前十的告警指标统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/alarmIndexOfTopTen")
    public R alarmIndexOfTopTen(@RequestParam Map<String, Object> queryMap){
        List<Map<String, Object>> result = saAmService.alarmIndexOfTopTen(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 告警等级分布统计（可按日、周、月来统计）
     * @param queryMap
     * @return
     */
    @RequestMapping("/distributionOfAlarmLevel")
    public R distributionOfAlarmLevel(@RequestParam Map<String, Object> queryMap){

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
        List<Map<String, Object>> result = saAmService.distributionOfAlarmLevel(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 备份异常情况统计
     * @param queryMap
     * @return
     */
    @RequestMapping("/backupExceptionSa")
    public R backupExceptionSa(@RequestParam Map<String, Object> queryMap){
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
        List<Map<String, Object>> result = saAmService.backupExceptionSa(queryMap);
        return R.ok().put("data", result);
    }

    /**
     * 告警数及解决率统计
     */
    @RequestMapping("/alarmSa")
    public R alarmSa(@RequestParam Map<String, Object> queryMap) {
        List<Map<String, Object>> result = saAmService.alarmSa(queryMap);
        return R.ok().put("data", result);
    }
}
