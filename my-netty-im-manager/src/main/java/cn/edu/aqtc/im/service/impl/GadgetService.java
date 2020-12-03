package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.service.inter.IGadgetService;
import cn.edu.aqtc.im.util.PPTXUtils;

import java.io.InputStream;

/**
 * @Description:
 * @ClassName: GadgetService
 * @Author: zhangjj
 * @Date: 2020-12-03
 */
public class GadgetService implements IGadgetService {
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
    @Override
    public String getPPTContent(String fileName, InputStream inputStream) {
        PPTXUtils.readPPT2007Stream(inputStream);
        return null;
    }
}
