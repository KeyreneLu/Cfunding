package com.jdyy.cfunding.http;

/**
 *
 * Created by Administrator on 2016/12/8 0008.
 */
public class ApiException extends RuntimeException {

    public static final String REQUEST_OK = "100";
    public static final String REQUEST_FORBID = "403";
    public static final String SERVER_CODE_ERROR = "500";
    public static final String SERVER_ERROR = "502";
    public static final String INTERFACE_ERROR = "503";
    public static final String INTERFACE_NO_EXIST = "404";
    public static final String SERVER_INTERNAL_ERROR = "401";
    public static final String NONE_TOKEN = "T01";
    public static final String TOKEN_ERROR = "T02";
    public static final String LOSE_LOGIN_STATUS = "405";
    public static final String NO_MORE_DATA = "109";
    public static final String USER_NAME_ERROR = "L01";
    public static final String USER_PASSWORD_ERROR = "L02";
    public static final String NONE_ERROR = "L03";


    public ApiException(String resultCode) {
        super(getApiExceptionMessage(resultCode));
    }
    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(String code){
        String message = "";
        switch (code) {
            case REQUEST_OK:
                message = "请求成功";
                break;
            case REQUEST_FORBID:
                message = "禁止访问";
                break;
            case SERVER_CODE_ERROR:
                message = "服务的代码错误";
                break;
            case SERVER_ERROR:
                message = "服务端服务错误";
                break;
            case INTERFACE_ERROR:
                message = "接口资源错误";
                break;
            case SERVER_INTERNAL_ERROR:
                message = "请求资源类型错误";
                break;
            case NONE_TOKEN:
                message = "Token不存在";
                break;
            case INTERFACE_NO_EXIST:
               message = "接口不存在";
                break;
            case TOKEN_ERROR:
                message = "Token错误";
                break;
            case LOSE_LOGIN_STATUS:
                message = "登录状态丢失";
                break;
            case NO_MORE_DATA:
                message = "没有更多数据";
                break;
            case USER_NAME_ERROR:
                message = "用户名错误";
                break;
            case USER_PASSWORD_ERROR:
                message = "密码错误";
                break;
            case NONE_ERROR:
                message = "未知错误";
                break;
        }
        return message;
    }
}

