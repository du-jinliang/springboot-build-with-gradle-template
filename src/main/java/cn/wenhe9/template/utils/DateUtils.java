package cn.wenhe9.template.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 时间格式化工具类
 * @author: DuJinliang
 * @create: 2023/11/11
 */
@UtilityClass
public class DateUtils {

    private final SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public String yyyyMMddFormat(Date date) {
        try {
            return yyyyMMddFormat.format(date);
        }catch (Exception e) {
            LogInfoUtils.errInfo(e);
            return null;
        }
    }

    public String yyyyMMddHHmmssSSSFormat(Date date) {
        try {
            return yyyyMMddHHmmssSSS.format(date);
        }catch (Exception e) {
            LogInfoUtils.errInfo(e);
            return null;
        }
    }
}
