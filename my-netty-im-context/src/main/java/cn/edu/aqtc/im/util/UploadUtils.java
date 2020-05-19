package cn.edu.aqtc.im.util;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName : UploadUtils
 * @Description : 上传工具类
 * @Author : zhangjj
 * @Date: 2020-05-17
 */
public class UploadUtils {

    public static String subFileName(String fileName) {
        // 查找最后一个 \ (文件分隔符)位置
        int index = fileName.lastIndexOf(File.separator);
        if (index == -1) {
            // 没有分隔符，说明是真实名称
            return fileName;
        } else {
            return fileName.substring(index + 1);
        }
    }

    public static String generateRandomFileName(String fileName) {
        // 首相获得扩展名，然后生成一个UUID码作为名称，然后加上扩展名
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + ext;
    }

    public static String generateRandomFileName() {
        return UUID.randomUUID().toString();
    }

    public static String generateRandomDir(String uuidFileName) {
        // 得到它的hashcode编码
        int hashCode = uuidFileName.hashCode();
        // 一级目录
        int d1 = hashCode & 0xf;
        // 二级目录
        int d2 = (hashCode >> 4) & 0xf;
        return "/" + d1 + "/" + d2;
    }


}
