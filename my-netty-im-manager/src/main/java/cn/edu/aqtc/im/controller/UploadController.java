package cn.edu.aqtc.im.controller;

import cn.edu.aqtc.im.VO.DownloadDocVO;
import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.code.UserBusiResultCode;
import cn.edu.aqtc.im.service.inter.IFileSystemService;
import cn.edu.aqtc.im.service.inter.IGadgetService;
import cn.edu.aqtc.im.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private IFileSystemService fileSystemService;
    @Autowired
    private IGadgetService gadgetService;
    @Autowired
    private HttpServletResponse response;


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
        if (!"jpg".equals(suffix) && !"jpeg".equals(suffix) && !"png".equals(suffix)) {
            return RestResult.getRestResult(UserBusiResultCode.UPLOAD_FILE_FORMAT_ERROR);
        }
        String uuId = UploadUtils.generateRandomFileName(fileName);
        String targetPath = UploadUtils.generateRandomDir(uuId) + "/" + uuId;
        fileSystemService.uploadFile(targetPath, file.getInputStream());

        return RestResult.getSuccessRestResult(targetPath);

    }


    @RequestMapping(value = "/uploadPPT")
    public RestResult<String> uploadPPT(@RequestParam("file") MultipartFile file) throws IOException {
        // 文件名
        String fileName = file.getOriginalFilename();
        // 在file文件夹中创建名为fileName的文件
        assert fileName != null;
        int split = fileName.lastIndexOf(".");
        // 文件后缀，用于判断上传的文件是否是合法的
        String suffix = fileName.substring(split + 1, fileName.length()).toLowerCase();
        //判断文件类型，因为我这边是图片，所以只设置三种合法格式
        String url = "";
        if (!"ppt".equals(suffix) && !"pptx".equals(suffix)) {
            return RestResult.getRestResult(UserBusiResultCode.UPLOAD_FILE_FORMAT_ERROR);
        }
        return RestResult.getSuccessRestResult(gadgetService.getPPTContent(fileName, file.getInputStream()));

    }


    @RequestMapping(value = "/downloadDoc")
    public void downloadDoc(@RequestBody DownloadDocVO downloadDocVO) {
        gadgetService.exportContent2Doc(downloadDocVO.getFileName(), downloadDocVO.getContent(), response);

    }
}
