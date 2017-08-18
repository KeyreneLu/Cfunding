package com.jdyy.cfunding.bean;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Invest {
    private String money;
    private String percent;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Invest(String money, String percent) {
        this.money = money;
        this.percent = percent;
    }
}
