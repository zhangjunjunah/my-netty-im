package cn.edu.aqtc.im.cache;

import cn.edu.aqtc.im.protocol.FriendMessage;
import cn.edu.aqtc.im.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 个人消息缓存
 * @ClassName: PersonalMsgCache
 * @Author: zhangjj
 * @Date: 2020-04-14
 */
@Component
public class PersonalMsgCache {
    private Cache cache;
    private Lock lock = new ReentrantLock();
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
        lock.lock();
        try {
            List<FriendMessage> friendMessages = cache.get(cacheKey, List.class);
            if (CommonUtils.objectIsNull(friendMessages)) {
                friendMessages = new CopyOnWriteArrayList<>();
                friendMessages.add(friendMessage);
                cache.put(cacheKey, friendMessages);
                return;
            }
            friendMessages.add(friendMessage);
            cache.put(cacheKey, friendMessages);
        } finally {
            lock.unlock();
        }
    }

    /**
     * @Description: 从缓存中取消息
     * @Param: [sender, receiver]
     * @return: java.util.List<cn.edu.aqtc.im.protocol.FriendMessage>
     * @Author: zhangjj
     * @Date: 2020-04-14
     */
    public List<FriendMessage> getMsg(String sender, String receiver) {
        String cacheKey = new StringBuilder(sender).append(":").append(receiver).toString();
        List<FriendMessage> friendMessages = cache.get(cacheKey, List.class);
        return friendMessages;
    }

}
