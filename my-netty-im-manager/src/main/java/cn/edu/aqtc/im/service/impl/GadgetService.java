package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.exception.ParseOfficeException;
import cn.edu.aqtc.im.service.inter.IGadgetService;
import cn.edu.aqtc.im.util.OfficeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Description:
 * @ClassName: GadgetService
 * @Author: zhangjj
 * @Date: 2020-12-03
 */
@Service
@Slf4j
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
    public void exportContent2Doc(String fileName, String content, HttpServletResponse response) {
        XWPFDocument document = OfficeUtils.writeDoc(content);
        response.setContentType("application/msword");
        try {
            response.setHeader("filename", URLEncoder.encode(FilenameUtils.getBaseName(fileName) + ".docx", "UTF-8"));
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(FilenameUtils.getBaseName(fileName) + ".docx", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ParseOfficeException(e);
        }
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            document.write(os);
        } catch (IOException e) {
            throw new ParseOfficeException(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new ParseOfficeException(e);
                }
            }
        }
    }
}
