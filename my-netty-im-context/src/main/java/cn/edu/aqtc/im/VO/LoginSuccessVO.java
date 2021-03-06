package cn.edu.aqtc.im.VO;

import cn.edu.aqtc.im.bean.ChatUser;
import cn.edu.aqtc.im.entity.ImUser;
import cn.edu.aqtc.im.transfer.FriendBean;
import cn.edu.aqtc.im.transfer.GroupBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description: 登录成功VO
 * @ClassName: LoginSuccessVO
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginSuccessVO {

    @JsonProperty("CHAT_USER")
    private ChatUser chatUser;

    @JsonProperty("IM_USER")
    private ImUser imUser;

    @JsonProperty("FRIEND_LIST")
    private List<ChatUser> friendList;

    @JsonProperty("FRIEND_REL")
    private List<GroupBean> friendRel;

    @JsonProperty("CONVERSATION_LIST")
    private List<FriendBean> conversationList;
}
