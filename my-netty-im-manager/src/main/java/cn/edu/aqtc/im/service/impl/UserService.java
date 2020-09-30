package cn.edu.aqtc.im.service.impl;

import cn.edu.aqtc.im.VO.ImFriendRelVO;
import cn.edu.aqtc.im.VO.LoginSuccessVO;
import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.bean.RestResult;
import cn.edu.aqtc.im.code.UserBusiResultCode;
import cn.edu.aqtc.im.constant.UserConstants;
import cn.edu.aqtc.im.entity.ImFriendRel;
import cn.edu.aqtc.im.entity.ImUser;
import cn.edu.aqtc.im.mapper.ImFriendRelMapper;
import cn.edu.aqtc.im.mapper.ImUserMapper;
import cn.edu.aqtc.im.service.inter.IConversationService;
import cn.edu.aqtc.im.service.inter.IUserService;
import cn.edu.aqtc.im.transfer.FriendBean;
import cn.edu.aqtc.im.transfer.GroupBean;
import cn.edu.aqtc.im.util.CommonUtils;
import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description:
 * @ClassName: UserService
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private ImUserMapper imUserMapper;


    @Autowired
    private ImFriendRelMapper imFriendRelMapper;

    @Autowired
    private ConversationCache conversationCache;

    @Autowired
    private IConversationService conversationService;

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
        ConcurrentMap<String, ChatUser> friendMap = new ConcurrentHashMap(UserConstants.getFriendCache().asMap());
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
            return RestResult.getRestResult(UserBusiResultCode.USER_UNREGISTERED);
        }
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setImUser(searchUser);
        //查询会话列表
        loginSuccessVO.setConversationList(conversationService.getConversionList(searchUser.getUserId().toString()));
        //查询朋友列表
        loginSuccessVO.setFriendRel(parseFriendRel(imFriendRelMapper.selectByUserId(searchUser.getUserId())));
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
     * @param queryMsg 查询信息可以是IM号、账号或昵称
     * @return java.util.List<cn.edu.aqtc.im.entity.ImUser>
     * @Description 查询用户
     * @Author zhangjj
     * @Date 2020-06-14
     **/
    @Override
    public List<ImUser> queryFriend(String queryMsg) {
        ImUser imUser = new ImUser();
        try {
            imUser.setUserId(Long.parseLong(queryMsg));
        } catch (NumberFormatException e) {
            log.info("queryMsg not number");
        }
        imUser.setUserName(queryMsg);
        imUser.setNickName(queryMsg);


        return imUserMapper.selectFuzzy(imUser);
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

    /**
     * @param imFriendRelList
     * @return java.util.List<cn.edu.aqtc.im.transfer.GroupBean>
     * @Description 解析好友列表
     * @Author zhangjj
     * @Date 2020-05-12
     **/
    private List<GroupBean> parseFriendRel(List<ImFriendRelVO> imFriendRelList) {
        Set<Long> groupIds = new HashSet<>(8);
        for (ImFriendRel imFriendRel : imFriendRelList) {
            if (!CommonUtils.objectIsNull(imFriendRel.getFriendId())) {
                continue;
            }
            groupIds.add(imFriendRel.getGroupId());
        }
        List<GroupBean> groupBeanList = new ArrayList<>(groupIds.size());
        for (long groupId : groupIds) {
            GroupBean group = new GroupBean();
            List<FriendBean> friendBeanList = new ArrayList<>(8);
            for (ImFriendRelVO imFriendRel : imFriendRelList) {
                if (groupId == imFriendRel.getParentGroupId()) {
                    friendBeanList.add(FriendBean.parseImFriendRel(imFriendRel));
                } else if (groupId == imFriendRel.getGroupId()) {
                    group.setGroupId(groupId);
                    group.setGroupName(imFriendRel.getGroupName());
                } else {
                    continue;
                }
            }
            group.setFriendList(friendBeanList);
            group.setFriendTotal(friendBeanList.size());
            groupBeanList.add(group);
        }
        return groupBeanList;
    }
}
