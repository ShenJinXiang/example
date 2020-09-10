package com.shenjinxiang.interaction.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileKit {

    private static final Logger logger = LoggerFactory.getLogger(FileKit.class);

    public static List<String> readFileLines(String filePath) {
        BufferedReader bufferedReader = null;
        List<String> lines = new ArrayList<>();
        try  {
            File file = new File(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            logger.error("读取文件出错", e);
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lines;
    }

    public static void main(String[] args) {
        String filePath = "D:\\temp\\20200909\\0f85e9f9-bda0-4d5b-9b9f-fc1aa4ab12a5.csv";
        File file = new File(filePath);
        FileLineReader fileLineReader = new FileLineReader(file);
        ThreadPool.getThread().execute(fileLineReader);
        int count = 0;
        while (true) {
            try {
                String str = fileLineReader.readLine();
                logger.info(str);
                count++;
                if (count == 200) {
//                    fileLineReader.close();
                    break;
                }
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fileLineReader = new FileLineReader(file);
        ThreadPool.getThread().execute(fileLineReader);
        while (true) {
            try {
                String str = fileLineReader.readLine();
                logger.info(str);
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
