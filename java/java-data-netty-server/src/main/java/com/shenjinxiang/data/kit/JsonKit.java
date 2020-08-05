package com.shenjinxiang.data.kit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 8:49
 */
public class JsonKit {

    private static final Logger logger = LoggerFactory.getLogger(JsonKit.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            logger.error("解析json字符串出错", e);
        }
        return null;
    }

    public static <T> T fromJson(String content, TypeReference<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            logger.error("解析json字符串出错", e);
        }
        return null;
    }

    public static <T> List<T> fromJsonAsList(String content, Class<T> valueType) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, valueType);
            return objectMapper.readValue(content, javaType);
        } catch (Exception e) {
            logger.error("解析json字符串出错", e);
        }
        return null;
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("转换到json字符串出错", e);
            return null;
        }
    }

}
