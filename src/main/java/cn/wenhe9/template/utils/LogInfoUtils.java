package cn.wenhe9.template.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @description: 日志打印工具类
 * @author: DuJinliang
 * @date: 2023/11/3
 */
@UtilityClass
public class LogInfoUtils {
    public String errInfo(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
