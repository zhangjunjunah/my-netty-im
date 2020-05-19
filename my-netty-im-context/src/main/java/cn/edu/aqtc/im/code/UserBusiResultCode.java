package cn.edu.aqtc.im.code;

/**
 * @Description: 用户业务返回码
 * @ClassName: UserBusiResultCode
 * @Author: zhangjj
 * @Date: 2020-04-13
 */
public enum UserBusiResultCode {
    //成功
    SUCCESS(200, null),
    //用户未注册
    USER_UNREGISTERED(20001, "用户未注册"),
    //用户登录密码错误
    USER_PASSWORD_ERROR(20002, "用户登录密码错误"),
    //用户已存在
    USER_ALREADY_EXISTS(20003, "用户已存在"),
    UPLOAD_FILE_FORMAT_ERROR(30001, "上传文件格式不正确，请上传jpg/jpeg/png");


    private int code;

    private String message;

    private UserBusiResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
