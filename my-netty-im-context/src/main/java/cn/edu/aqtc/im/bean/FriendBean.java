package cn.edu.aqtc.im.bean;

import cn.edu.aqtc.im.VO.ImFriendRelVO;
import cn.edu.aqtc.im.constant.UserStatusEnum;
import lombok.Data;

/**
 * @ClassName : FriendBean
 * @Description : 好友
 * @Author : zhangjj
 * @Date: 2020-05-12
 */
@Data
public class FriendBean {

    private Long friendId;

    private String friendName;

    private String remarkName;

    private String avatarSrc;

    private UserStatusEnum userStatus;


    public static FriendBean parseImFriendRel(ImFriendRelVO imFriendRel) {
        FriendBean friendBean = new FriendBean();
        friendBean.setFriendId(imFriendRel.getFriendId());
        friendBean.setFriendName(imFriendRel.getFriendName());
        friendBean.setRemarkName(imFriendRel.getRemarkName());
        friendBean.setAvatarSrc(imFriendRel.getAvatarSrc());
        return friendBean;
    }


}
