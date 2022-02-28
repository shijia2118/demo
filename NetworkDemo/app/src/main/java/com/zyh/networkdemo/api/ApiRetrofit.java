package com.zyh.networkdemo.api;

import static com.zyh.networkdemo.api.ApiService.BASE_HOST;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiRetrofit {

    private final ApiService apiService;
    private static ApiRetrofit apiRetrofit;
    private static final String TAG = "ApiRetrofit";

    //构造函数
    public ApiRetrofit(){
        //网络日志
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(s -> {
            try {
                String text = URLDecoder.decode(s, "utf-8");
                Log.e(TAG, text);
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
                Log.e(TAG, s);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //配置OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor) //添加日志
                .connectTimeout(30, TimeUnit.SECONDS) //请求时限 30s
                .readTimeout(30, TimeUnit.SECONDS) //读取时限 30s
                .writeTimeout(30, TimeUnit.SECONDS) //写入时限 30s
                .build();

        //配置Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_HOST) //基础域名
                .addConverterFactory(GsonConverterFactory.create()) //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(ScalarsConverterFactory.create()) //增加返回值为String的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //怎加返回值为Observable<T>的支持
                .client(client) //绑定OkHttpClient
                .build();

        apiService = retrofit.create(ApiService.class); //创建api实例
    }

    //双重检查，实现单例
    public static ApiRetrofit getInstance(){
        if(apiRetrofit == null){
            //使用同步锁，当有线程在执行时，保证其它线程不会进入该方法体.
            synchronized (Object.class){
                if(apiRetrofit == null){
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiService getApiService(){ return apiService; }

}

