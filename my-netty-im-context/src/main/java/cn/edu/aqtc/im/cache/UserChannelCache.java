package cn.edu.aqtc.im.cache;

import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * @Description: 存放登录用户与Channel关系缓存
 * @ClassName: UserChannelCache
 * @Author: zhangjj
 * @Date: 2020-04-10
 */
@Component
public class UserChannelCache {

    @Autowired
    private CacheManager cacheManager;

    private Cache cache;

    public UserChannelCache() {
        this.cache = cacheManager.getCache("userChannel");
    }

    /**
     * @Description: 将userId与ctx放入缓存
     * @Param: [userId, ctx]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-10
     */
    public void putUserChannel2Cache(String userId, Channel channel) {
        cache.put(userId, channel);
    }


    public Channel getChannelByUserId(String userId) {
        return (Channel) cache.get(userId);
    }

    public void removeUserChannel(String userId) {
        cache.evict(userId);
    }
}
