package com.hdjtlgbbs.program.util;

import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class ProgramUtil {
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
