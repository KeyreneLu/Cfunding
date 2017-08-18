package com.jdyy.cfunding.bean;

/**
 * Created by Administrator on 2016/11/26 0026.
 */

public class Token {
    /**
     * msg : success
     * code : 00
     * result : {"token":"b451697f5d3fab8a5990dd4a412b38f68e560097"}
     */

    private String msg;
    private String code;
    /**
     * token : b451697f5d3fab8a5990dd4a412b38f68e560097
     */
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override
    public String toString() {
        return "Token{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", result=" + result +
                '}';
    }
}
