package cn.edu.aqtc.im.cache;

import cn.edu.aqtc.im.protocol.FriendMessage;
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
    public void pushMsg(Long user1, Long user2, FriendMessage friendMessage) {
        String cacheKey1 = new StringBuilder(user1.toString()).append(":").append(user2).toString();
        String cacheKey2 = new StringBuilder(user2.toString()).append(":").append(user1).toString();
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            List<FriendMessage> friendMessages1 = cache.get(cacheKey1, List.class);
            List<FriendMessage> friendMessages2 = cache.get(cacheKey2, List.class);
            if (null == friendMessages1 && null == friendMessages2) {
                friendMessages1 = new CopyOnWriteArrayList<>();
                friendMessages1.add(friendMessage);
                cache.put(cacheKey1, friendMessages1);
            } else if (null == friendMessages1) {
                friendMessages2.add(friendMessage);
                cache.put(cacheKey2, friendMessages2);
            } else {
                friendMessages1.add(friendMessage);
                cache.put(cacheKey1, friendMessages1);
            }
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
    public List<FriendMessage> getMsg(Long user1, Long user2) {
        String cacheKey1 = new StringBuilder(user1.toString()).append(":").append(user2).toString();
        List<FriendMessage> friendMessages = cache.get(cacheKey1, List.class);
        if (friendMessages != null) {
            return friendMessages;
        }
        String cacheKey2 = new StringBuilder(user2.toString()).append(":").append(user1).toString();
        return cache.get(cacheKey2, List.class);

    }

}
