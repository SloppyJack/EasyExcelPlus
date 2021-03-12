package cn.jackbin.easyexcelplus.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtil {
    public static byte[] convert2Bytes(String path) {
        try {
            return new FileInputStream(path).readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
