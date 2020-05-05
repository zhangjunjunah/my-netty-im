package cn.edu.aqtc.im.controller;

import cn.edu.aqtc.im.bean.RestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName : UploadController
 * @Description : 上传相关请求
 * @Author : zhangjj
 * @Date: 2020-05-02
 */
@RestController
@RequestMapping(value = "/api/upload")
public class UploadController {


    @RequestMapping(value = "/uploadAvatar")
    public RestResult<String> uploadAvatar(@RequestParam("file") MultipartFile file) throws IOException {
        // 文件名
        String fileName = file.getOriginalFilename();
        // 在file文件夹中创建名为fileName的文件
        assert fileName != null;
        int split = fileName.lastIndexOf(".");
        // 文件后缀，用于判断上传的文件是否是合法的
        String suffix = fileName.substring(split + 1, fileName.length());
        //判断文件类型，因为我这边是图片，所以只设置三种合法格式
        String url = "";
        if ("jpg".equals(suffix) || "jpeg".equals(suffix) || "png".equals(suffix)) {
            // 正确的类型，保存文件
        } else {
            //错误的类型，返回错误提示

        }
        return RestResult.getSuccessRestResult(fileName);
    }
}
