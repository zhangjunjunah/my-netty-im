package cn.edu.aqtc.im.constant;

import cn.edu.aqtc.im.bean.ChatUser;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;

/**
 * @Description: 在没有数据库前，代替数据库
 * @ClassName: UserConstants
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
public class UserConstants {

    private static Cache<String, ChatUser> chatUserCache = CacheBuilder.newBuilder()
            .build();

    private static Cache<String, ChatUser> friendCache = CacheBuilder.newBuilder()
            .build();

    static {
        List<ChatUser> chatUserList = ChatUser.loadChatUserList();
        for (ChatUser chatUser : chatUserList) {
            chatUserCache.put(chatUser.getUserId(), chatUser);
        }
        List<ChatUser> friendList = ChatUser.loadFriendList();
        for (ChatUser chatUser : friendList) {
            friendCache.put(chatUser.getUserId(), chatUser);
        }
    }

    public static Cache<String, ChatUser> getChatUserCache() {
        return chatUserCache;
    }

    public static Cache<String, ChatUser> getFriendCache() {
        return friendCache;
    }

    public static Cache<String, ChatUser> updateFriendCache(ChatUser chatUser) {
        friendCache.put(chatUser.getUserId(), chatUser);
        return friendCache;
    }
}
