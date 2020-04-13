package cn.edu.aqtc.im.code;

/**
 * @Description: 用户业务返回码
 * @ClassName: UserBusiResultCode
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
public enum UserBusiResultCode {
    //成功
    SUCCESS(200),
    //用户未注册
    USER_UNREGISTERED(20001),
    //用户登录密码错误
    USER_PASSWORD_ERROR(20002);

    private int code;

    private UserBusiResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
