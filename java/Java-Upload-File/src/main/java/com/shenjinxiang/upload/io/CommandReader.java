package com.shenjinxiang.upload.io;

import com.shenjinxiang.upload.core.Config;
import com.shenjinxiang.upload.kit.FileUtil;
import com.shenjinxiang.upload.kit.JsonKit;
import com.shenjinxiang.upload.kit.StrKit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/7 22:00
 */
public class CommandReader implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CommandReader.class);

    private static final String UFILE = "ufile";
    private static final String ENCODE = "encode";
    private static final String DECODE = "decode";
    private static final String INFO = "info";
    private static final String EXIT = "exit";

    private BufferedReader bufferedReader;

    public CommandReader() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }

    @Override
    public void run() {
        while (true) {
            logger.info("请输入命令: ");
            try {
                String line = bufferedReader.readLine();
                if (StrKit.notBlank(line) && !"".equals(line.trim())) {
                    logger.info("录入内容：" + line);
                    String[] words = line.trim().split("\\s+");
                    if (ENCODE.equalsIgnoreCase(words[0])) {
                        if (words.length <= 1) {
                            logger.info("输入文件名！");
                            continue;
                        }
                        String filePath = words[1];
                        String str = FileUtil.base64File(filePath);
                        logger.info("内容：\n" + str);
                        continue;
                    }
                    if(UFILE.equalsIgnoreCase(words[0])) {
                        if (words.length <= 1) {
                            logger.info("输入文件名！");
                            continue;
                        }
                        uploadFileStr(words[1]);
                        continue;
                    }
                    if (EXIT.equalsIgnoreCase(words[0])) {
                        logger.info("程序结束！");
                        System.exit(0);
                    }
                    logger.info("没有任务!");
                }
            } catch (Exception e) {
                logger.error("处理任务出错", e);
            }

        }
    }

    private void uploadFileStr(String filePath) {
        try {
            String fileStr = FileUtil.base64File(filePath);
            String fileLx = "image";
            String hzm = FileUtil.suffix(new File(filePath));
            logger.info("读取文件完成！");
            Map<String, String> data = new HashMap<>();
            data.put("fileStr", fileStr);
            data.put("fileLx", fileLx);
            data.put("hzm", hzm);

            String url = Config.URL + Config.UPLOAD_FILE_STR_URI;
            logger.info("上传文件：" +
                    "\n\t 请求路径：" + url +
                    "\n\t fileLx" + fileLx +
                    "\n\t hzm" + hzm +
                    "\n\t fileStr" + fileStr
            );
            Connection.Response resp = Jsoup.connect(url)
                    .data(data)
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute();

            int code = resp.statusCode();
            logger.info("结果: " + code);
            String jsonStr = resp.body();
            Map<String, Object> result = JsonKit.fromJson(jsonStr, Map.class);
            String message = (String) result.get("message");
            Map<String, Object> msgMap = JsonKit.fromJson(message, Map.class);
            String path = (String) msgMap.get("path");
            String fileUrl = Config.BASE_FILE_URL + path;
            logger.info("文件路径：" + fileUrl);
        } catch (Exception e) {
            logger.error("出错了！", e);
        }

    }

//        /Users/shenjinxiang/shenjinxiang/images/30173438530.jpg
//    /Users/shenjinxiang/shenjinxiang/images/1920x1080.jpg
//     /Users/shenjinxiang/shenjinxiang/images/1.jpg
}
