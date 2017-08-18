package com.jdyy.cfunding.http;

/**
 * Created by Administrator on 2016/11/26 0006.
 */
public class HttpResult<T> {

    private String msg;
    private String code;
    private T result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T results) {
        this.result = results;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("msg=" + msg);
        sb.append("code=" + code);
        if (null != result) {
            sb.append(" result:" + result.toString());
        }
        return sb.toString();
    }
}
