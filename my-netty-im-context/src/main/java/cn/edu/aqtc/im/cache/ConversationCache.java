package cn.edu.aqtc.im.cache;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.entity.ImUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : ConversationCache
 * @Description : 会话缓存
 * @Author : zhangjj
 * @Date: 2020-05-07
 */
@Component
public class ConversationCache {

    private Cache cache;

    @Autowired
    public ConversationCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("conversation");
    }


    public List<ChatUser> getConversation(Long userId) {
        return (List<ChatUser>) cache.get(userId);
    }

    public void setConversation(Long userId, ImUser imUser) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            List<ImUser> imUserList = (List<ImUser>) cache.get(userId);
            if (imUser == null) {
                imUserList = new ArrayList<>(8);
                imUserList.add(imUser);
                cache.put(userId, imUserList);
                return;
            }
            imUserList.add(0, imUser);
            cache.put(userId, imUserList);
        } finally {
            lock.unlock();
        }
    }

}
