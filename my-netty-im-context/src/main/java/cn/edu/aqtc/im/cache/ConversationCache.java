package cn.edu.aqtc.im.cache;

import cn.edu.aqtc.im.transfer.FriendBean;
import cn.edu.aqtc.im.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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

    private Lock lock = new ReentrantLock();

    @Autowired
    public ConversationCache(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("conversation");
    }


    public List<FriendBean> getConversation(String userId) {
        return (List<FriendBean>) cache.get(userId, List.class);
    }

    public void addConversation(String userId, FriendBean friendBean) {
        lock.lock();
        try {
            List<FriendBean> imUserList = (List<FriendBean>) cache.get(userId, List.class);
            if (CommonUtils.objectIsNull(imUserList)) {
                imUserList = new ArrayList<>(8);
                imUserList.add(friendBean);
                cache.put(userId, imUserList);
                return;
            }
            int index = imUserList.indexOf(friendBean);
            if (index != -1) {
                Collections.swap(imUserList, index, 0);
                cache.put(userId, imUserList);
                return;
            }
            imUserList.add(0, friendBean);
            cache.put(userId, imUserList);
        } finally {
            lock.unlock();
        }
    }

}
