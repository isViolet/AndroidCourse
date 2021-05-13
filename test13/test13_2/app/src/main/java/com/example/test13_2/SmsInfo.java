package com.example.test13_2;

class SmsInfo {
    private String address;//发送地址
    private long date;//发送时间
    private int type;//类型
    private String body;//内容
    private int id;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //构造方法
    public SmsInfo(String address, long date, int type, String body) {
        this.address = address;
        this.date = date;
        this.type = type;
        this.body = body;
    }
}