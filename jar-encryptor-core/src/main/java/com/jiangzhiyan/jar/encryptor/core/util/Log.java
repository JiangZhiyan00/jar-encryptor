package com.jiangzhiyan.jar.encryptor.core.util;


import com.jiangzhiyan.jar.encryptor.core.Const;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 控制台打印日志工具
 */
@UtilityClass
public class Log {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 输出debug信息
     *
     * @param msg 信息
     */
    public static void debug(Object msg) {
        if (Const.DEBUG) {
            System.out.println(DATE_TIME_FORMATTER.format(LocalDateTime.now()) + " [DEBUG] " + msg);
        }
    }

    /**
     * 输出
     *
     * @param obj 内容
     */
    public static void println(String obj) {
        System.out.println(obj);
    }

    /**
     * 输出
     *
     * @param obj 内容
     */
    public static void print(String obj) {
        System.out.print(obj);
    }

    /**
     * 输出
     */
    public static void println() {
        System.out.println();
    }
}
