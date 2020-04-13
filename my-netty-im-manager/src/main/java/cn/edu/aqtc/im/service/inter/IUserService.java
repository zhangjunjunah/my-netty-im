package cn.edu.aqtc.im.service.inter;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.RestResult;

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
}
