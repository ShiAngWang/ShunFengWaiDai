package com.example.sfwd.shunfengwaidai.sun;

import java.io.Serializable;

public class Info implements Serializable{
    private int picture;
    private String fname,ftime,state,detail,price,style,distance,fphone,addr,dtime,dname,dphone;
    public Info(int picture, String fname, String ftime, String state, String detail, String price, String style, String distance, String fphone, String addr, String dtime, String dname, String dphone) {
        super();
        this.picture = picture;
        this.fname = fname;
        this.ftime = ftime;
        this.state = state;
        this.detail = detail;
        this.price = price;
        this.style = style;
        this.distance = distance;//????
        this.fphone = fphone;//???????
        this.addr = addr;//??????
        this.dtime = dtime;//?????????
        this.dname = dname;//?????????
        this.dphone = dphone;//??????
    }
    public Info(){
        super();
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getAdd() {
        return addr;
    }

    public void setAdd(String add) {
        this.addr = add;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }
}
