package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.service.inter.IGadgetService;
import cn.edu.aqtc.im.util.OfficeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
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
        if (StringUtils.endsWith(fileName.toLowerCase(), "ppt")) {
            return OfficeUtils.readTextPPT2003Stream(inputStream);
        } else {
            return OfficeUtils.readTextPPT2007Stream(inputStream);
        }
    }

    @Override
    public void exportContent2Doc(String fileName, String content, HttpServletResponse httpServletResponse) {

    }
}
