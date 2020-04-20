package cn.edu.aqtc.im.bean;

import cn.edu.aqtc.im.constant.UserStatusEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description: 聊天用户信息
 * @ClassName: ChatUser
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
@Data
@Slf4j
public class ChatUser implements Serializable {
    private static File chatUserFile = new File(ChatUser.class.getClassLoader().getResource("chatUser.json").getPath());
    private static File friendFile = new File(ChatUser.class.getClassLoader().getResource("friends.json").getPath());
    private String userId;
    private String userName;
    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 用户状态(在线/离线)
     */
    private UserStatusEnum userStatus;


    /**
     * @Description: 加载用户列表
     * @Param: []
     * @return: java.util.List<cn.edu.aqtc.im.bean.ChatUser>
     * @Author: zhangjj
     * @Date: 2020-04-13
     */
    public static List<ChatUser> loadChatUserList() {
        return getChatUsers(chatUserFile);
    }

    /**
     * @Description: 加载好友列表 ，好友列表目前就是用户列表去除自己
     * @Param: []
     * @return: java.util.List<cn.edu.aqtc.im.bean.ChatUser>
     * @Author: zhangjj
     * @Date: 2020-04-13
     */
    public static List<ChatUser> loadFriendList() {
        return getChatUsers(friendFile);
    }

    private static List<ChatUser> getChatUsers(File friendFile) {
        if (!friendFile.exists()) {
            log.warn("chatUserFile is not exists");
            return null;
        }
        String jsonStr = null;
        try {
            jsonStr = readFromFile(friendFile);
        } catch (IOException e) {
            log.error("jsonStr read error");
        }
        if (jsonStr == null) {
            return null;
        }
        return JSONObject.parseArray(jsonStr, ChatUser.class);
    }


    private static String readFromFile(File file) throws IOException {
        return IOUtils.toString(new FileInputStream(file), Charset.defaultCharset());
    }

}
