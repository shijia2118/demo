package com.zyh.networkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zyh.networkdemo.base.BaseActivity;
import com.zyh.networkdemo.base.BasePresenter;
import com.zyh.networkdemo.base.BaseView;
import com.zyh.networkdemo.presenter.LoginPresenter;
import com.zyh.networkdemo.ui.LoginView;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return null;
    }


    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailed() {

    }
}