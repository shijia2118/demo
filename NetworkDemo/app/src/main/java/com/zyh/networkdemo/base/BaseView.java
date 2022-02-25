package com.zyh.networkdemo.base;

   public interface BaseView {

    /**
     * 显示dialog
     */
    void showLoading(String message);

    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     *
     * @param msg 消息内容
     */
    void showError(String msg);

}
