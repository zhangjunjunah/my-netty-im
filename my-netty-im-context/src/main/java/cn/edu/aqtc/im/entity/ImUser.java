package cn.edu.aqtc.im.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ImUser {
    private Long userId;

    private String userName;

    private String password;

    private String nickName;

    private String avatarSrc;

    private String phoneNum;

    private String mail;

    private Date createTime;

}