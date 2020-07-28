package com.shenjinxiang.transform.kit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shenjinxiang.transform.domain.TransformGroup;
import com.shenjinxiang.transform.domain.TransformReceive;
import com.shenjinxiang.transform.domain.TransformSend;
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

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("转换到json字符串出错", e);
            return null;
        }
    }

        public static void main(String[] args) throws Exception {
//        String str = "{\n" +
//                "      \"type\": \"UDP\",\n" +
//                "      \"ip\": \"127.0.0.1\",\n" +
//                "      \"port\": 5001,\n" +
//                "      \"dataHandler\": \"com.shenjinxiang.transform.handler.UdpDataHandler\"\n" +
//                "    }";
//        TransformReceive receive = JsonKit.fromJson(str, TransformReceive.class);
//        System.out.println(receive.getDataHandler());
//        System.out.println(receive.getIp());
//        System.out.println(receive.getPort());
//        System.out.println(receive.getType());


        String str = "[\n" +
                    "  {\n" +
                    "    \"enable\": true,\n" +
                    "    \"receive\": {\n" +
                    "      \"type\": \"UDP\",\n" +
                    "      \"ip\": \"127.0.0.1\",\n" +
                    "      \"port\": 5001,\n" +
                    "      \"dataHandler\": \"com.shenjinxiang.transform.handler.UdpDataHandler\"\n" +
                    "    },\n" +
                    "    \"send\": [\n" +
                    "      {\n" +
                    "        \"type\": \"TCP\",\n" +
                    "        \"ip\": \"127.0.0.1\",\n" +
                    "        \"port\": 5001\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"type\": \"TCP\",\n" +
                    "        \"ip\": \"127.0.0.1\",\n" +
                    "        \"port\": 5002\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"enable\": true,\n" +
                    "    \"receive\": {\n" +
                    "      \"type\": \"TCP\",\n" +
                    "      \"ip\": \"127.0.0.1\",\n" +
                    "      \"port\": 6001,\n" +
                    "      \"dataHandler\": \"com.shenjinxiang.transform.handler.TcpDataHandler\"\n" +
                    "    },\n" +
                    "    \"send\": [\n" +
                    "      {\n" +
                    "        \"type\": \"TCP\",\n" +
                    "        \"ip\": \"127.0.0.1\",\n" +
                    "        \"port\": 6002\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"type\": \"TCP\",\n" +
                    "        \"ip\": \"127.0.0.1\",\n" +
                    "        \"port\": 6002\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "]";
            List<TransformGroup> groupList = fromJson(str, new TypeReference<List<TransformGroup>>() { });
//            for(TransformGroup group: groupList) {
//                TransformReceive receive = group.getReceive();
//                List<TransformSend> send = group.getSend();
//                boolean enable = group.isEnable();
//                System.out.println("enable: " + enable);
//                System.out.println("receive: ");
//                System.out.println("\t type: " + receive.getType());
//                System.out.println("\t ip: " + receive.getIp());
//                System.out.println("\t port: " + receive.getPort());
//                System.out.println("\t handler: " + receive.getDataHandler());
//                System.out.println("send:");
//                for(TransformSend s: send) {
//                    System.out.println("\t type: " + s.getType());
//                    System.out.println("\t ip: " + s.getIp());
//                    System.out.println("\t port: " + s.getPort());
//                    System.out.println();
//                }
//            }
    }

}
