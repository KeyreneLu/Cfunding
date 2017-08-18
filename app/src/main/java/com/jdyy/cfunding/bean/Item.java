package com.jdyy.cfunding.bean;

/**
 * Created by Administrator on 2016/11/26 0026.
 */

public class Item {
    /**
     * name : 悠趣智路由 - 孩子上网的安全卫士
     * id : 125
     * desc : 悠趣智路由是首款针对未成年人绿色上网安全管理的智能路由器，它在拥有传统智能路由器的一键配置、上网加速、手机控制等功能外，更具备未成年人绿色上网功能，可实现未成年人上网设备的内容管理、时间管理与远程控制，并针对不同年龄段的未成年人提供优质互联网推荐内容，合理安排上网时间，营造健康积极的上网环境。
     * cover_img : /uploads/6725/20160524/zmlcms_1464081697116.jpg
     * start_time : 0
     * end_time : 1467043200
     * raising_money : 149.00
     * view : 0
     * cid : 2
     * catName : 文创
     */

    private String name;
    private String id;
    private String desc;
    private String cover_img;
    private String start_time;
    private String end_time;
    private String raising_money;
    private String view;
    private String cid;
    private String catName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRaising_money() {
        return raising_money;
    }

    public void setRaising_money(String raising_money) {
        this.raising_money = raising_money;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
