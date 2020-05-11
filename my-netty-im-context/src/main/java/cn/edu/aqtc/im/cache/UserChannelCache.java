package cn.edu.aqtc.im.cache;

import cn.edu.aqtc.im.bean.ChatUser;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Component;

/**
 * @Description: 存放登录用户与Channel关系缓存
 * @ClassName: UserChannelCache
 * @Author: zhangjj
 * @Date: 2020-04-10
 */
@Component
public class UserChannelCache {


    private Cache<Long, Channel> cache = CacheBuilder.newBuilder()
            .build();


    private Cache<String, ChatUser> channelUserCache = CacheBuilder.newBuilder()
            .build();
    ;
    /**
     * netty 客户端组
     */
    public static ChannelGroup channelGroup;

    /**
     * @Description: 将userId与ctx放入缓存
     * @Param: [userId, ctx]
     * @return: void
     * @Author: zhangjj
     * @Date: 2020-04-10
     */
    public void putUserChannel2Cache(Long userId, Channel channel) {
        cache.put(userId, channel);
    }

    public void putChannelUser2Cache(String channelId, ChatUser chatUser) {
        channelUserCache.put(channelId, chatUser);
    }


    public Channel getChannelByUserId(String userId) {
        return cache.getIfPresent(userId);
    }

    public ChatUser getUserByChannelId(String channelId) {
        return channelUserCache.getIfPresent(channelId);
    }

    public void removeUserChannel(Long userId) {
        cache.invalidate(userId);
    }

    public void removeChannelUser(String channelId) {
        channelUserCache.invalidate(channelId);
    }
}
