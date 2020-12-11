package cn.edu.aqtc.im.util;


import cn.edu.aqtc.im.exception.ParseOfficeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @Description:
 * @ClassName: PPTXUtils
 * @Author: zhangjj
 * @Date: 2020-11-18
 */
@Slf4j
public class OfficeUtils {

    public static void main(String[] args) throws Exception {

        File file = new File("F:\\windows\\library file\\Desktop\\word excel ppt 2003官方完整版.ppt");
        try {
            FileInputStream fin = new FileInputStream(file);
            // String cont=readDoc1(fin);
            //System.out.println(cont);
            //fin.close();
            fin = new FileInputStream(file);
            //System.out.println(readPPT2007("F:\\windows\\library file\\Desktop\\发热病人的护理.pptx"));
            String content = readTextPPT2003Stream(fin);
            writeDoc(content);
            System.out.println();
            fin.close();
        } catch (IOException e) {
            throw new ParseOfficeException(e);
        }

    }

    //直接抽取幻灯片的全部内容
    public static String readDoc1(InputStream is) throws IOException {
        PowerPointExtractor extractor = new PowerPointExtractor(is);
        return extractor.getText();
    }

    public static String readPPT2007(String file) throws IOException, XmlException, OpenXML4JException {
        String content = new XSLFPowerPointExtractor(POIXMLDocument.openPackage(file)).getText();
        return content.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
    }


    public static String readTextPPT2003Stream(InputStream inputStream) {
        StringBuffer content = new StringBuffer("");
        try {
            // path为文件的全路径名称，建立SlideShow
            SlideShow ss = new SlideShow(new HSLFSlideShow(inputStream));
            // 获得每一张幻灯片
            Slide[] slides = ss.getSlides();
            for (int i = 0; i < slides.length; i++) {
                // 为了取得幻灯片的文字内容，建立TextRun
                TextRun[] t = slides[i].getTextRuns();
                for (int j = 0; j < t.length; j++) {
                    // 这里会将文字内容加到content中去
                    content.append(t[j].getText());
                }
                content.append(slides[i].getTitle());
            }
        } catch (IOException e) {
            throw new ParseOfficeException(e);
        }
        return content.toString();

    }

    public static XWPFDocument writeDoc(String content) {
        //创建一个空白文档
        XWPFDocument document = new XWPFDocument();
        //创建一个段落
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                XWPFRun run = paragraph.createRun();
                run.setText(line);
                run.addBreak();
            }
            return document;
        } catch (FileNotFoundException e) {
            throw new ParseOfficeException(e);
        } catch (IOException e) {
            throw new ParseOfficeException(e);
        }
    }

    public static String readTextPPT2007Stream(InputStream inputStream) {
        String content = null;
        try {
            content = new XSLFPowerPointExtractor(OPCPackage.open(inputStream)).getText();
        } catch (XmlException e) {
            throw new ParseOfficeException(e);
        } catch (OpenXML4JException e) {
            throw new ParseOfficeException(e);
        } catch (IOException e) {
            throw new ParseOfficeException(e);
        }
        log.info("readTextPPT2007Stream end");
        return content.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
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
