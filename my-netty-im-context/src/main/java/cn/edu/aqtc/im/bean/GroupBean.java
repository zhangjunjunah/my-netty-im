package cn.edu.aqtc.im.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName : GroupBean
 * @Description : 分组对象
 * @Author : zhangjj
 * @Date: 2020-05-12
 */
@Data
public class GroupBean {

    private List<FriendBean> friendList;

    private Long groupId;

    private String groupName;

    private int friendTotal;

    private int onLineFriendTotal;


}
