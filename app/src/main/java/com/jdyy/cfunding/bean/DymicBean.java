package com.jdyy.cfunding.bean;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class DymicBean extends RealmObject {
    private String id;
    private String time;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DymicBean{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
