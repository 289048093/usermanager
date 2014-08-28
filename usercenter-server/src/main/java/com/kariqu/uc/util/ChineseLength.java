package com.kariqu.uc.util;

/**
 * @author: Koala
 * @Date: 14-7-7 上午10:20
 * @Version: 1.0
 */
public class ChineseLength {

    /**
     * 中文为两个字符
     * @param value
     * @return
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }
}
