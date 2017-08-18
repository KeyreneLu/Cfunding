package com.jdyy.cfunding.bean;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class Constant {

    public static final String MAIN_LOG = "CFunding";
    public static final String Main_URL ="http://t.zamamall.cn/";
    public static final String APP_ID = "Z1";
    public static final String APP_SECRET = "2844d5eaac88835d";
    public static final String WEN_CHUANG = "文创";
    public static final String KE_JI = "科技";
    public static final String NONG_YE = "农业";
    public static final int POINT_STATE_NORMAL = 0; //默认状态
    public static final int POINT_STATE_SELECTED = 1; //按下状态

    public static final String IMAGE_CACHE_PATH = Environment.getExternalStorageDirectory().toString()
            + File.separator + "syt" + File.separator + "image" + File.separator;
    public static final String ACTION_DOWNLOAD_PROGRESS = "my_download_progress";
    public static final String ACTION_DOWNLOAD_SUCCESS = "my_download_success";
    public static final String ACTION_DOWNLOAD_FAIL = "my_download_fail";

}
