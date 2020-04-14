package cn.edu.aqtc.im.cache;

import cn.edu.aqtc.im.bean.FriendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 个人消息缓存
 * @ClassName: PersonalMsgCache
 * @Author: zhangjj
 * @Date: 2020-04-14
 */
public class PersonalMsgCache {
    private Cache cache;

    @Autowired
    public PersonalMsgCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("personalMsg");
    }

    /**
     * @Description: 将消息推送到缓存
     * @Param: [sender, receiver, friendMessage]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-14
     */
    public void pushMsg(String sender, String receiver, FriendMessage friendMessage) {
        String cacheKey = new StringBuilder(sender).append(":").append(receiver).toString();
        List<FriendMessage> friendMessages = cache.get(cacheKey, List.class);
        if (null == friendMessages) {
            friendMessages = new CopyOnWriteArrayList<>();
        }
        friendMessages.add(friendMessage);
    }

    /**
     * @Description: 从缓存中取消息
     * @Param: [sender, receiver, friendMessage]
     * @return: java.util.List<cn.edu.aqtc.im.bean.FriendMessage>
     * @Author: zhangjj
     * @Date: 2020-04-14
     */
    public List<FriendMessage> getMsg(String sender, String receiver, FriendMessage friendMessage) {
        String cacheKey = new StringBuilder(sender).append(":").append(receiver).toString();
        List<FriendMessage> friendMessages = cache.get(cacheKey, List.class);
        return friendMessages;
    }

}
