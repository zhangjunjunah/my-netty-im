package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.VO.LoginSuccessVO;
import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.code.UserBusiResultCode;
import cn.edu.aqtc.im.service.inter.IUserService;
import cn.edu.aqtc.im.util.UserConstants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description:
 * @ClassName: UserService
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
@Service
public class UserService implements IUserService {
    /**
     * @param chatUser
     * @Description: 登录
     * @Param: [chatUser]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-13
     */
    @Override
    public RestResult login(ChatUser chatUser) {
        ChatUser searchUser = UserConstants.getChatUserCache().getIfPresent(chatUser.getUserId());
        if (searchUser == null) {
            return RestResult.getRestResult(UserBusiResultCode.USER_UNREGISTERED.getCode());
        }
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setChatUser(searchUser);
        ConcurrentMap<String, ChatUser> friendMap = UserConstants.getFriendCache().asMap();
        //移除自己
        friendMap.remove(searchUser.getUserId());
        loginSuccessVO.setFriendList(new ArrayList<>(friendMap.values()));
        return RestResult.getSuccessRestResult(loginSuccessVO);
    }
}
