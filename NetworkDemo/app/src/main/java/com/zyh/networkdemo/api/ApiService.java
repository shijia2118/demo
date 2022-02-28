package com.zyh.networkdemo.api;

import com.zyh.networkdemo.base.BaseModel;
import com.zyh.networkdemo.model.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServer {
    String BASE_HOST = "https://mock.apipost.cn/app/mock/project/74ca9bd8-917d-4615-90f0-98e9e5001f38/";  //[ApiPost]mock测试地址

    //用户登录
    @POST("demo/network/login")
    Observable<BaseModel<LoginBean>> userLogin(@Body Map<String,Object> map);
}
