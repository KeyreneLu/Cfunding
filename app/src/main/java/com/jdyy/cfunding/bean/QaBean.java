package com.jdyy.cfunding.bean;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class QaBean extends RealmObject {
    private String title;
    private String content;
    private String time;
    private String answer;
    private String acontent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAcontent() {
        return acontent;
    }

    public void setAcontent(String acontent) {
        this.acontent = acontent;
    }

    @Override
    public String toString() {
        return "QaBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", answer='" + answer + '\'' +
                ", acontent='" + acontent + '\'' +
                '}';
    }
}
