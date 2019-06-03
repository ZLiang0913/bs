package net.wanho.util;

import java.util.regex.Pattern;

/**
 * 工具类
 */
public class Common {

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(isInteger("sdf222a"));
    }
}
