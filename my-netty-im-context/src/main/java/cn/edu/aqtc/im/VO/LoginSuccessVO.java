package cn.edu.aqtc.im.VO;

import cn.edu.aqtc.im.bean.ChatUser;
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

    @JsonProperty("FRIEND_LIST")
    private List<ChatUser> friendList;
}
