package com.jdyy.cfunding.http;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class GlobalToken {
    private static String sToken;

    public static synchronized void updateToken(String token) {
        sToken = token;
    }

    public static String getToken() {
        return sToken;
    }
}
