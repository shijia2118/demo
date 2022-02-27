package com.zyh.networkdemo.presenter;

import com.zyh.networkdemo.base.BaseModel;
import com.zyh.networkdemo.base.BaseObserver;
import com.zyh.networkdemo.base.BasePresenter;
import com.zyh.networkdemo.model.LoginBean;
import com.zyh.networkdemo.ui.LoginView;

import java.util.Map;

public class LoginPresenter extends BasePresenter<LoginView> {
    LoginView loginView;

    //通过构造函数传递LoginView
    public LoginPresenter(LoginView loginView){this.loginView = loginView;}

    public void login(Map<String,Object> map){
        BaseObserver<BaseModel<LoginBean>> baseObserver = new BaseObserver<BaseModel<LoginBean>>(loginView) {
            @Override
            public void onSuccess(BaseModel<LoginBean> o) {
                loginView.onLoginSuccess(o.getData());
            }

            @Override
            public void onError() {
                loginView.onLoginFailed();
            }
        };
        addDisposable(apiServer.userLogin(map),baseObserver);
    }
}
