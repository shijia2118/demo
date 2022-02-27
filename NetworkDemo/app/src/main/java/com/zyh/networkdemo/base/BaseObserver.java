package com.zyh.networkdemo.base;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    protected BaseView view;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;

    public BaseObserver(BaseView view){
        this.view = view;
    }

    @Override
    public void onStart(){
        if(view != null){
            view.hideLoading();
            view.showLoading("");
        }
    }

    @Override
    public void onNext(@NotNull T o) {
        try {
            int code;
            String message = "";
            if (o instanceof BaseModel) {
                BaseModel<?> model = (BaseModel<?>) o;
                code = model.getCode();
                message = model.getDesc();
            } else if (o instanceof BaseListModel) {
                BaseListModel<?> model = (BaseListModel<?>) o;
                code = model.getCode();
                message = model.getMessage();
            } else {
                code = BaseModel.CODE_SUCCESS;
            }
            if (code == BaseModel.CODE_SUCCESS) {
                onSuccess(o);
            }else {
                view.showError(message);
                onError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.showError(e.toString());
            onError();
        }
    }

    @Override
    public void onError( Throwable e) {
        if (view != null){
            view.hideLoading();
        }

        if (e instanceof HttpException) {
            //HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            //连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //解析错误
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
               if(view != null) view.showError(e.toString());
            } else {
                if(view != null) view.showError("未知错误");
            }
            onError();
        }

    }

    /**
     * 异常情况
     * @param unknownError 异常情况对应的数值
     */
    private void onException(int unknownError) {
        String message = "";
        switch (unknownError) {
            case CONNECT_ERROR:
                message = "连接错误";
                break;

            case CONNECT_TIMEOUT:
                message = "连接超时";
                break;

            case BAD_NETWORK:
                message = "网络问题";
                break;

            case PARSE_ERROR:
                message = "解析数据失败";
                break;

            default:
                break;
        }
        view.showError(message);
        onError();
    }

    @Override
    public void onComplete() {
        //完成后隐藏加载框
        if (view != null) view.hideLoading();
    }

    public abstract void onSuccess(T o);

    public abstract void onError();
}
