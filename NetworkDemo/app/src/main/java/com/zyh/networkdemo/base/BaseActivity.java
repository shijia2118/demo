package com.zyh.networkdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zyh.networkdemo.widgets.LoadingDialog;

public abstract class BaseActivity<P extends BasePresenter<? extends BaseView>> extends AppCompatActivity implements BaseView{

    private LoadingDialog dialog;
    public Context context;
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(getLayoutId());
        presenter = createPresenter();
        initView();
        initData();
    }

    /**
     * 关闭加载框
     */
    private void closeLoadingDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 显示加载框
     * @param message 加载框内容
     */
    private void showLoadingDialog(String message) {

        if (dialog == null) {
            dialog = new LoadingDialog(context);
        }
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 吐司弹窗
     */
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，
            //同时显示光标，反之，需要隐藏键盘、光标
            if (isShouldHideInput(v, ev)) {
                //处理EditText的光标隐藏、显示逻辑
                closeKeyboard((EditText) v, this);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 关闭软键盘的方法（写在 KeyBoardUtils 的工具类里面）
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeyboard(EditText mEditText, Context mContext) {
        if (mEditText != null && mContext != null) {
            InputMethodManager imm = (InputMethodManager) mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }
    }

    /**
     * 判断点击的区域是否EditText之外
     * @param v 视图
     * @param event 手势事件
     * @return 返回true说明点击的是输入框区域外
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            // 点击的是输入框区域，保留点击EditText的事件
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        return false;
    }

    @Override
    public void showLoading(String message) {
        showLoadingDialog(message);
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    protected abstract int getLayoutId();

    public abstract void initData();

    public abstract void initView();

    protected abstract P createPresenter();
}
