package cn.edu.aqtc.im.service.inter;

import java.io.InputStream;

/**
 * @ClassName : FileSystemService
 * @Description : 文件系统服务
 * @Author : zhangjj
 * @Date: 2020-05-17
 */
public interface IFileSystemService {

    /**
     * @param targetPath
     * @param inputStream
     * @return boolean
     * @Description 上传文件
     * @Author zhangjj
     * @Date 2020-05-17
     **/
    boolean uploadFile(String targetPath, InputStream inputStream);
}
