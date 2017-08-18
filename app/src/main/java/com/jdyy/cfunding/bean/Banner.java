package com.jdyy.cfunding.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class Banner implements Parcelable {
    /**
     * url : http://www.zamamall.cn/News/info/id/21.html
     * img : /uploads//20160522/zmlcms_1463911251450.jpg
     * name : 新版上线
     */

    private String url;
    private String img;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.img);
        dest.writeString(this.name);
    }

    public Banner() {
    }

    protected Banner(Parcel in) {
        this.url = in.readString();
        this.img = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Banner> CREATOR = new Parcelable.Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel source) {
            return new Banner(source);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
        }
    };
}
