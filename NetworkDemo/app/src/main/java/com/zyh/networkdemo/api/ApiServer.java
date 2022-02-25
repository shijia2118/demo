package com.zyh.networkdemo.api;

import com.zyh.networkdemo.base.BaseModel;
import com.zyh.networkdemo.model.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiServer {
    String BASE_HOST = "http://192.168.1.13:8022/";  //测试地址

    //用户登录
    @POST("api/User/UserLogin")
    Observable<BaseModel<LoginBean>> userLogin(@HeaderMap Map<String, Object> headers, @Body RequestBody route);
}
