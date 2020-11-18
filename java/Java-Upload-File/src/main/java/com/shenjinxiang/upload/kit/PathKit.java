package com.shenjinxiang.upload.kit;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/6/3 9:14
 */
public class PathKit {

    public static boolean isJar() {
        URL url = PathKit.class.getResource("");
        String protocol = url.getProtocol();
        return "jar".equalsIgnoreCase(protocol);
    }

    public static String getCrrentFilePath() throws IllegalAccessException {
        return getCurrentFile().getAbsolutePath();
    }

    public static String getCurrentPath() throws IllegalAccessException {
        return getCurrentFile().getParent();
    }

    private static File getCurrentFile() throws IllegalAccessException {
        ProtectionDomain protectionDomain = PathKit.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URI location = null;
        try {
            location = null == codeSource ? null : codeSource.getLocation().toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String path = null == location ? null : location.getSchemeSpecificPart();
        if (null == path) {
            throw new IllegalAccessException("unable to determine code source archive");
        }
        File root = new File(path);
        if (!root.exists()) {
            throw new IllegalAccessException("Unable to determine code source archive from " + root);
        }
        return root;
    }
}
