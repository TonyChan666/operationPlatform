package cn.com.bmsoft.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 计算时间前进后退
     * @param date 当前时间
     * @param type Calendar.HOUR
     * @param num 计算值
     * @return
     */
    public static Date dateRoll(Date date, int type, int num) {
        // 获取Calendar对象并以传进来的时间为准
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 将现在的时间滚动固定时长,转换为Date类型赋值
        calendar.add(type, num);
        // 转换为Date类型再赋值
        date = calendar.getTime();
        return date;
    }

    /**
     * 时间格式化
     * @param date 时间
     * @param type "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String dataFormat(Date date,String type){
        SimpleDateFormat sf = new SimpleDateFormat(type);
        return sf.format(date);
    }
}
