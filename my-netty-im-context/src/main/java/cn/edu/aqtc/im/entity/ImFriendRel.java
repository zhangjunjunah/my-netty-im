package cn.edu.aqtc.im.entity;

import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import lombok.Data;

@Data
public class ImFriendRel {
    private Long friendRel;

    private Long groupId;

    private String groupName;

    private Long friendId;

    private String friendName;

    private Long parentGroupId;

    private Long userId;

    public static ImFriendRel getDefaultRel(Long userId) {
        ImFriendRel imFriendRel = new ImFriendRel();
        imFriendRel.setFriendRel(SnowflakeIdWorker.getSequenceId());
        imFriendRel.setGroupId(SnowflakeIdWorker.getSequenceId());
        imFriendRel.setGroupName("我的好友");
        imFriendRel.setParentGroupId(0L);
        imFriendRel.setUserId(userId);
        return imFriendRel;
    }

    public static ImFriendRel getDefaultRel(Long userId, Long parentGroupId) {
        ImFriendRel imFriendRel = new ImFriendRel();
        imFriendRel.setFriendRel(SnowflakeIdWorker.getSequenceId());
        imFriendRel.setParentGroupId(parentGroupId);
        imFriendRel.setFriendId(100000000000000000L);
        imFriendRel.setFriendName("机锋小摩托");
        imFriendRel.setUserId(userId);
        return imFriendRel;
    }

}