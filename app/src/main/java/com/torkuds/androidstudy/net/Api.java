package com.torkuds.androidstudy.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final int CONNECT_TIMEOUT = 10;

    public static String baseUrl = "http://192.168.1.5:8080/";

    private static volatile Api mInstance;

    private Retrofit mRetrofit;

    private ApiService apiService;

    private Api(){
        init();
    }

    public static ApiService getService(){
        if (mInstance == null){
            synchronized (Api.class){
                if (mInstance == null){
                    mInstance = new Api();
                }
            }
        }
        return mInstance.apiService;
    }

    private void init(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd-HH:mm:ss").create();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        apiService = mRetrofit.create(ApiService.class);
    }

    public <T> T create(Class<T> service){
        if (service == null) {
            throw  new RuntimeException("Api service is null!");
        }
        return mRetrofit.create(service);
    }
}
