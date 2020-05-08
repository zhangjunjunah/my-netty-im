package cn.edu.aqtc.im.service.inter;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.entity.ImUser;

/**
 * @Description: 用户服务
 * @ClassName: IUserService
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
public interface IUserService {

    /**
     * @Description: 登录
     * @Param: [chatUser]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-13
     */
    RestResult login(ChatUser chatUser);


    /**
     * @param imUser
     * @return cn.edu.aqtc.im.bean.RestResult
     * @Description 登录
     * @Author zhangjj
     * @Date 2020-05-07
     **/
    RestResult login(ImUser imUser);


    /**
     * @param imUser
     * @return cn.edu.aqtc.im.bean.RestResult
     * @Description 用户注册
     * @Author zhangjj
     * @Date 2020-05-02
     **/
    RestResult register(ImUser imUser);
}
