package com.jdyy.cfunding.bean;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class Classify {
    /**
     * id : 83
     * name : 远安香菇酱
     */

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classify(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
