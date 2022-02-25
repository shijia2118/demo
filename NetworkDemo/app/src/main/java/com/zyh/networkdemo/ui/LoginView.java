package com.zyh.networkdemo.ui;

import com.zyh.networkdemo.base.BaseView;

public interface LoginView extends BaseView {
    /**
     * 登录一般只有2种状态：成功或失败
     */

    void onLoginSuccess();  //登录成功

    void onLoginFailed(); //登录失败
}
