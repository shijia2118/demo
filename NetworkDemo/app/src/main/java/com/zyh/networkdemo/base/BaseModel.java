package com.zyh.networkdemo.base;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {
    public final static int CODE_SUCCESS = 0;

    private int code;
    private T data;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
