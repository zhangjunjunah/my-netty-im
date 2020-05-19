package cn.edu.aqtc.im.entity;

import cn.edu.aqtc.im.constant.Constants;
import cn.edu.aqtc.im.util.SnowflakeIdWorker;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImFriendRel implements Serializable {
    private Long friendRel;

    private Long groupId;

    private String groupName;

    private Long friendId;

    private String friendName;

    private String remarkName;

    private Long parentGroupId;

    private Long userId;


    public static ImFriendRel getDefaultRel(Long userId) {
        ImFriendRel imFriendRel = new ImFriendRel();
        imFriendRel.setFriendRel(SnowflakeIdWorker.getSequenceId());
        imFriendRel.setGroupId(SnowflakeIdWorker.getSequenceId());
        imFriendRel.setGroupName(Constants.DEFAULT_GROUP_NAME);
        imFriendRel.setParentGroupId(0L);
        imFriendRel.setUserId(userId);
        return imFriendRel;
    }

    public static ImFriendRel getDefaultRel(Long userId, Long parentGroupId) {
        ImFriendRel imFriendRel = new ImFriendRel();
        imFriendRel.setFriendRel(SnowflakeIdWorker.getSequenceId());
        imFriendRel.setParentGroupId(parentGroupId);
        imFriendRel.setFriendId(Constants.ADMIN_USER_ID);
        imFriendRel.setFriendName(Constants.ADMIN_USERNAME);
        imFriendRel.setUserId(userId);
        imFriendRel.setRemarkName(Constants.ADMIN_REMARK_NAME);
        return imFriendRel;
    }
}