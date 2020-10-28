package com.shenjinxiang.upload.kit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.UUID;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static long size(File file) {
        if (null == file || !file.exists()) {
            return 0;
        }
        if (file.isDirectory()) {
            return 0;
        }
        return file.length();
    }

    public static String sizeStr(File file) {
        long size = size(file);
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static String suffix(File file) {
        if (null == file) {
            return "";
        }
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }

    public static void mkdirs(File file) {
        if (null == file) {
            return;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public static String randomFileName(String hzm) {
        String random = UUID.randomUUID().toString().replace("-", "");
        if (StrKit.isBlank(hzm)) {
            return random;
        }
        return random + "." + hzm;
    }

    public static void copyFile(File fromFile, File toFile) throws Exception {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            mkdirs(toFile);
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buff)) != -1) {
                fileOutputStream.write(buff, 0, len);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != fileInputStream) {
                fileInputStream.close();
            }
            if (null != fileOutputStream) {
                fileOutputStream.close();
            }
        }
    }

    public static void writeFile(File file, byte[] bytes) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            mkdirs(file);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (Exception e) {
            logger.error("字节写入文件出错", e);
            throw e;
        } finally {
            if (null != fileOutputStream) {
                fileOutputStream.close();
            }
        }
    }

    public static byte[] readFile(File file) {
        FileInputStream fileInputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            fileInputStream = new FileInputStream(file);
//            ByteBuf byteBuf = Unpooled.buffer();
            byte[] bytes = new byte[1024 * 10];
            int len = 0;
            while ((len = fileInputStream.read(bytes)) != -1) {
//                byteBuf.writeBytes(bytes);
                byteArrayOutputStream.write(bytes, 0, len);
            }
//            byte[] message = new byte[byteBuf.readableBytes()];
//            return message;
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            logger.error("读取文件出错！", e);
            return null;
        } finally {
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("关闭文件流出错", e);
                }
            }
        }
    }

    public static String base64File(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            logger.info("文件不存在！");
            return null;
        }
        if (!file.isFile()) {
            logger.info("指定文件，而不是目录！");
            return null;
        }
        return base64File(file);
    }

    public static String base64File(File file) {
        if (null == file) {
            logger.info("文件不存在！");
            return null;
        }
        byte[] buff = readFile(file);
        if (buff == null) {
            return null;
        }
        byte[] encode = Base64Helper.encode(buff);
        return new String(encode, StandardCharsets.UTF_8);
    }


}
