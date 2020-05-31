package cn.edu.aqtc.im.transfer;

import cn.edu.aqtc.im.entity.ImUser;
import lombok.Data;

/**
 * @ClassName : StartConversationBean
 * @Description : 发起会话传输对象
 * @Author : zhangjj
 * @Date: 2020-05-30
 */
@Data
public class StartConversationBean {

    private Long userId;

    private ImUser myBean;

    private FriendBean friendBean;

}
