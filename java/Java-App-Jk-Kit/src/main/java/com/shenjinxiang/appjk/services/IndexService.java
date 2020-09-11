package com.shenjinxiang.appjk.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shenjinxiang.appjk.entity.Result;
import com.shenjinxiang.appjk.utils.DESUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/9/4 22:02
 */
@Service
public class IndexService {

    private static final Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private ObjectMapper objectMapper;

    public Result tjData(String url, String params, int check) {
        Result result = new Result();
        try {
            result.setParamStr(params);
            String param = params;
            if (check == 1) {
                param = DESUtil.encrypt(params);
            }
            result.setParam(param);
            logger.info("提交数据" +
                    "\n\turl: " + url +
                    "\n\tparams: " + param
            );
            Connection connection = Jsoup.connect(url);
            Connection.Response response = connection.data("params", param)
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .execute();
            String resultStr = response.body();
            result.setResultStr(resultStr);
            logger.info("提交数据" +
                    "\n\turl: " + url +
                    "\n\tparams: " + param +
                    "\n\tresult: " + resultStr
            );
            if (check == 1) {
                Map<String, Object> map = objectMapper.readValue(resultStr, Map.class);
                String message = (String) map.get("message");
                message = DESUtil.decrypt(message);
                map.put("message", message);
                result.setResult(objectMapper.writeValueAsString(map));
                result.setSuccess(true);
                return result;
            }
            result.setResult(resultStr);
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            logger.error("提交数据出现错误",e );
            return Result.error(e.getMessage());
        }
    }
}
