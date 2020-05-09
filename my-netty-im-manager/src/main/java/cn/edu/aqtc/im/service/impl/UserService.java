package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.VO.LoginSuccessVO;
import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.cache.ConversationCache;
import cn.edu.aqtc.im.code.UserBusiResultCode;
import cn.edu.aqtc.im.constant.UserConstants;
import cn.edu.aqtc.im.entity.ImFriendRel;
import cn.edu.aqtc.im.entity.ImUser;
import cn.edu.aqtc.im.mapper.ImFriendRelMapper;
import cn.edu.aqtc.im.mapper.ImUserMapper;
import cn.edu.aqtc.im.service.inter.IUserService;
import cn.edu.aqtc.im.util.CommonUtils;
import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description:
 * @ClassName: UserService
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private ImUserMapper imUserMapper;


    @Autowired
    private ImFriendRelMapper imFriendRelMapper;

    @Autowired
    private ConversationCache conversationCache;

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
        ConcurrentMap<String, ChatUser> friendMap = new ConcurrentHashMap<>(UserConstants.getFriendCache().asMap());
        //移除自己
        friendMap.remove(searchUser.getUserId());
        loginSuccessVO.setFriendList(new ArrayList<>(friendMap.values()));
        return RestResult.getSuccessRestResult(loginSuccessVO);
    }

    /**
     * @param imUser
     * @return cn.edu.aqtc.im.bean.RestResult
     * @Description 登录
     * @Author zhangjj
     * @Date 2020-05-07
     **/
    @Override
    public RestResult login(ImUser imUser) {
        ImUser searchUser = imUserMapper.selectByName(imUser.getUserName());
        if (searchUser == null) {
            return RestResult.getRestResult(UserBusiResultCode.USER_UNREGISTERED.getCode());
        }
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        //查询会话列表
        loginSuccessVO.setConversationList(conversationCache.getConversation(searchUser.getUserId()));
        //查询朋友列表
        loginSuccessVO.setFriendRels(imFriendRelMapper.selectByUserId(searchUser.getUserId()));
        return RestResult.getSuccessRestResult(loginSuccessVO);
    }

    /**
     * @param imUser
     * @return cn.edu.aqtc.im.bean.RestResult
     * @Description 用户注册
     * @Author zhangjj
     * @Date 2020-05-02
     **/
    @Override
    public RestResult register(ImUser imUser) {
        ImUser search = imUserMapper.selectByName(imUser.getUserName());
        if (!CommonUtils.objectIsNull(search)) {
            RestResult restResult = new RestResult();
            restResult.setCode(UserBusiResultCode.USER_ALREADY_EXISTS.getCode());
            restResult.setMessage(UserBusiResultCode.USER_ALREADY_EXISTS.getMessage());
            return restResult;
        }
        imUser.setUserId(SnowflakeIdWorker.getSequenceId());
        //插入用户表
        imUserMapper.insertSelective(imUser);
        //插入系统朋友
        initDefaultFriend(imUser.getUserId());
        return RestResult.getSuccessRestResult();
    }

    /**
     * @param userId
     * @return void
     * @Description 插入系统管理员
     * @Author zhangjj
     * @Date 2020-05-02
     **/
    private void initDefaultFriend(Long userId) {
        ImFriendRel imFriendRel = ImFriendRel.getDefaultRel(userId);
        imFriendRelMapper.insertSelective(imFriendRel);
        imFriendRelMapper.insertSelective(ImFriendRel.getDefaultRel(userId, imFriendRel.getGroupId()));
    }
}
