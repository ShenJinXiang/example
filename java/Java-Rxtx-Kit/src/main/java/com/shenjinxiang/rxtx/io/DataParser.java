package com.shenjinxiang.rxtx.io;

import com.shenjinxiang.rxtx.kit.ByteArrayConveter;
import com.shenjinxiang.rxtx.kit.ByteKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataParser {

    private static final Logger logger = LoggerFactory.getLogger(RxtxHandler.class);

    private static final String[] ledArr = {
            "S8", "S7", "S6", "S5", "S4", "S3", "S2", "S1",
            "S16", "S15", "S14", "S13", "S12", "S11", "S10", "S9",
            "S24", "S23", "S22", "S21", "S20", "S19", "S18", "S17",
            "S32", "S31", "S30", "S29", "S28", "S27", "S26", "S25"
    };

    private static final String[] pressArr = {
            "S8", "S7", "S6", "S5", "S4", "S3", "S2", "S1",
            "S16", "S15", "S14", "S13", "S12", "S11", "S10", "S9",
            "S24", "S23", "S22", "S21", "S20", "S19", "S18", "S17",
            "", "S31", "S30", "S29", "S28", "S27", "S26", "S25"
    };

    private static final Pattern lesPattern = Pattern.compile("[1]");
    private static final Pattern pressPattern = Pattern.compile("[0]");

    public static void parse(byte[] bytes) {
        String hex = ByteKit.byteArrayToHexStr(bytes);
        StringBuilder stringBuffer = new StringBuilder();
        StringBuilder ledStringBuilder = new StringBuilder();
        StringBuilder pressStringBuilder = new StringBuilder();
        stringBuffer.append("接收到数据：");
        stringBuffer.append("\n\t字节数组：").append(Arrays.toString(bytes));
        stringBuffer.append("\n\t十六进制：").append(hex);
        String led1Str = ByteKit.getBit(bytes[4]);
        String led2Str = ByteKit.getBit(bytes[5]);
        String led3Str = ByteKit.getBit(bytes[6]);
        String led4Str = ByteKit.getBit(bytes[7]);
        ledStringBuilder.append(led1Str);
        ledStringBuilder.append(led2Str);
        ledStringBuilder.append(led3Str);
        ledStringBuilder.append(led4Str);
        String press1Str = ByteKit.getBit(bytes[8]);
        String press2Str = ByteKit.getBit(bytes[9]);
        String press3Str = ByteKit.getBit(bytes[10]);
        String press4Str = ByteKit.getBit(bytes[11]);
        pressStringBuilder.append(press1Str);
        pressStringBuilder.append(press2Str);
        pressStringBuilder.append(press3Str);
        pressStringBuilder.append(press4Str);
        byte[] bytes1 = new byte[] {0x00, 0x00, bytes[12], bytes[13]};
        byte[] bytes2 = new byte[] {0x00, 0x00, bytes[14], bytes[15]};
        stringBuffer.append("\n\tLED灯数据：")
                .append(led1Str).append("   ")
                .append(led2Str).append("    ")
                .append(led3Str).append("    ")
                .append(led4Str);
        Matcher ledMatcher = lesPattern.matcher(ledStringBuilder.toString());
        stringBuffer.append("\n\t亮灯的键：");
        while (ledMatcher.find()) {
            stringBuffer.append(ledArr[ledMatcher.start()]).append("    ");
        }
        stringBuffer.append("\n\t按键数据：")
                .append(press1Str).append("    ")
                .append(press2Str).append("    ")
                .append(press3Str).append("    ")
                .append(press4Str);
        Matcher pressMatcher = pressPattern.matcher(pressStringBuilder.toString());
        stringBuffer.append("\n\t按下的键：");
        while (pressMatcher.find()) {
            String press = pressArr[pressMatcher.start()];
            if (!"".equals(press)) {
                stringBuffer.append(press).append("    ");
            }
        }
        stringBuffer.append("\n\t摇杆数据：")
                .append(ByteArrayConveter.getInt(bytes1, 0)).append("    ")
                .append(ByteArrayConveter.getInt(bytes2, 0));
        logger.info(stringBuffer.toString());
    }
}
