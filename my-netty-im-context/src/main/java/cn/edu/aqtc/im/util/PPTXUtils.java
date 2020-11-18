package cn.edu.aqtc.im.util;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @ClassName: PPTXUtils
 * @Author: zhangjj
 * @Date: 2020-11-18
 */
public class PPTXUtils {

    public static void main(String[] args) throws Exception {

        File file = new File("F:\\windows\\library file\\Desktop\\发热病人的护理.pptx");
        try {
            FileInputStream fin = new FileInputStream(file);
            // String cont=readDoc1(fin);
            //System.out.println(cont);
            //fin.close();
            fin = new FileInputStream(file);
            System.out.println(readPPT2007("F:\\windows\\library file\\Desktop\\发热病人的护理.pptx"));
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //直接抽取幻灯片的全部内容
    public static String readDoc1(InputStream is) throws IOException {
        PowerPointExtractor extractor = new PowerPointExtractor(is);
        return extractor.getText();
    }

    public static String readPPT2007(String file) throws IOException, XmlException, OpenXML4JException {
        return new XSLFPowerPointExtractor(POIXMLDocument.openPackage(file)).getText();
    }

    //一张幻灯片一张幻灯片地读取
    public static void readDoc2(InputStream is) throws IOException {
        SlideShow ss = new SlideShow(new HSLFSlideShow(is));
        Slide[] slides = ss.getSlides();
        for (int i = 0; i < slides.length; i++) {
            //读取一张幻灯片的标题
            String title = slides[i].getTitle();
            System.out.println("标题:" + title);
            //读取一张幻灯片的内容(包括标题)
            TextRun[] runs = slides[i].getTextRuns();
            for (int j = 0; j < runs.length; j++) {
                System.out.println(runs[j].getText());
            }
        }
    }

}
