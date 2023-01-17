package com.allspark.uhelper.exception;

/**
 * @ClassName BusinessExceptionCode
 * @Description TODO
 * @Author 86159
 * @Date 2022/10/20 23:25
 * @Version 1.0
 **/
public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_REPEAT("您已点赞过"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
