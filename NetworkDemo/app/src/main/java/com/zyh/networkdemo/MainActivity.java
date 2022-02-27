package com.zyh.networkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zyh.networkdemo.base.BaseActivity;
import com.zyh.networkdemo.model.LoginBean;
import com.zyh.networkdemo.presenter.LoginPresenter;
import com.zyh.networkdemo.ui.LoginView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginView {

    EditText account;
    EditText password;
    TextView confirmedLogin;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        account = findViewById(R.id.account);
        password = findViewById(R.id.password);
        confirmedLogin = findViewById(R.id.confirmed_btn);

        confirmedLogin.setOnClickListener(onLoginHandler);
    }

    /**
     * 点击登录按钮事件
     */
    private final View.OnClickListener onLoginHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(account.getText().toString().isEmpty()){
                Toast.makeText(context, "请输入账号", Toast.LENGTH_SHORT).show();
            }else if(password.getText().toString().isEmpty()){
                Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("username",account.getText().toString());
                map.put("password",password.getText().toString());
                presenter.login(map);
            }
        }
    };

    @Override
    protected LoginPresenter createPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }


    @Override
    public void onLoginSuccess(LoginBean data) {
        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {

    }
}