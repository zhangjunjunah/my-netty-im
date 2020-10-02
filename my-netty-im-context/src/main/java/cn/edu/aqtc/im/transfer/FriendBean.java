package cn.edu.aqtc.im.transfer;

import cn.edu.aqtc.im.VO.ImFriendRelVO;
import cn.edu.aqtc.im.constant.Constants;
import cn.edu.aqtc.im.constant.UserStatusEnum;
import cn.edu.aqtc.im.entity.ImUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : FriendBean
 * @Description : 好友
 * @Author : zhangjj
 * @Date: 2020-05-12
 */
@Data
public class FriendBean implements Serializable {

    private String friendId;

    private String friendName;

    private String remarkName;

    private String avatarSrc;

    private UserStatusEnum userStatus;


    public static FriendBean parseImFriendRel(ImFriendRelVO imFriendRel) {
        FriendBean friendBean = new FriendBean();
        friendBean.setFriendId(imFriendRel.getFriendId().toString());
        friendBean.setFriendName(imFriendRel.getFriendName());
        friendBean.setRemarkName(imFriendRel.getRemarkName());
        friendBean.setAvatarSrc(imFriendRel.getAvatarSrc());
        return friendBean;
    }

    public static FriendBean toFriendBean(ImUser imUser) {
        FriendBean friendBean = new FriendBean();
        friendBean.setAvatarSrc(imUser.getAvatarSrc());
        friendBean.setFriendId(imUser.getUserId().toString());
        friendBean.setFriendName(imUser.getUserName());
        return friendBean;
    }


    /**
     * @param
     * @return cn.edu.aqtc.im.transfer.FriendBean
     * @Description 获取新朋友会话
     * @Author zhangjj
     * @Date 2020-06-29
     **/
    public static FriendBean getNewFriendConversion() {
        FriendBean friendBean = new FriendBean();
        friendBean.setFriendId(Constants.NEW_FRIEND_USER_ID);
        friendBean.setUserStatus(UserStatusEnum.ONLINE);
        friendBean.setFriendName(Constants.NEW_FRIEND_USERNAME);
        friendBean.setRemarkName(Constants.NEW_FRIEND_REMARK_NAME);
        friendBean.setAvatarSrc(Constants.NEW_FRIEND_AVATAR_SRC);
        return friendBean;

    }
}
