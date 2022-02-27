package com.zyh.networkdemo.ui;

import com.zyh.networkdemo.base.BaseView;
import com.zyh.networkdemo.model.LoginBean;

public interface LoginView extends BaseView {
    /**
     * 登录一般只有2种状态：成功或失败
     * @param data
     */

    void onLoginSuccess(LoginBean data);  //登录成功

    void onLoginFailed(); //登录失败
}
