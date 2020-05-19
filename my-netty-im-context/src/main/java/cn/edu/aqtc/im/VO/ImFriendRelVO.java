package cn.edu.aqtc.im.VO;

import cn.edu.aqtc.im.entity.ImFriendRel;
import lombok.Data;

/**
 * @ClassName : ImFriendRelVO
 * @Description : 好友列表展示对象
 * @Author : zhangjj
 * @Date: 2020-05-17
 */
@Data
public class ImFriendRelVO extends ImFriendRel {

    private String avatarSrc;

    /**
     * 标语
     */
    private String slogan;

}
