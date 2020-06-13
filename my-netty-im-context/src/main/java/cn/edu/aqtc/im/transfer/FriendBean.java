package cn.edu.aqtc.im.transfer;

import cn.edu.aqtc.im.VO.ImFriendRelVO;
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


}
