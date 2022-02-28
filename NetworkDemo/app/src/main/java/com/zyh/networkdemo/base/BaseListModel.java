package com.zyh.networkdemo.base;

import java.io.Serializable;
import java.util.List;

public class BaseListModel<T> implements Serializable {

    private int code;
    private List<T> data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}