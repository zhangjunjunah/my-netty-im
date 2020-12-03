package cn.edu.aqtc.im.service.inter;

import java.io.InputStream;

/**
 * @Description:
 * @ClassName: GadgetService
 * @Author: zhangjj
 * @Date: 2020-12-03
 */
public interface IGadgetService {


    /**
     * 获取ppt内容
     *
     * @param fileName
     * @param inputStream
     * @throws
     * @return: java.lang.String
     * @Author: zhangjj
     * @Date: 2020-12-03
     */
    String getPPTContent(String fileName, InputStream inputStream);
}
