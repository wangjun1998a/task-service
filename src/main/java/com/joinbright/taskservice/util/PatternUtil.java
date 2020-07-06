package com.joinbright.taskservice.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author alin
 */
@Component
public class PatternUtil {
    /**
     * 公司邮箱地址匹配
     *
     * @param str 字符串
     * @return bool
     */
    public boolean patternMail(String str) {
        String pattern = "^\\w+([-+.]\\w+)*@hzzh+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        // 邮箱格式匹配
        return m.matches();
    }
}
