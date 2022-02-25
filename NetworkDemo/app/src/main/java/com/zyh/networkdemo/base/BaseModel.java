package com.zyh.networkdemo.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {
    // 建议自定义一个serialVersionUID,因为默认的SerialVersionUID
    // 对于class的细节非常敏感，反序列化时可能会导致InvalidClassException异常。
    private final static long SerialVersionUID = 1L;

    public static final int CODE_SUCCESS=0;
    public static final int CODE_ERROR=-1;

    @SerializedName("Success")
    private boolean success;
    @SerializedName("Code")
    private int code;
    @SerializedName("Message")
    private String message;
    @SerializedName("TotalCount")
    private int totalCount;
    @SerializedName("Data")
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
