package com.dianping.hui.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lanxinyu@meituan.com  2018-10-22 上午11:20
 * @Description:
 */
@Slf4j
public class JSONUtils {

    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            log.error("json parseObject \nclazz「{}」 \njson 「{}」 error", clazz.getName() ,json, e);
            return null;
        }
    }
}
