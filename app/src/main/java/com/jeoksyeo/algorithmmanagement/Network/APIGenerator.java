package com.jeoksyeo.algorithmmanagement.Network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIGenerator {
    private static Retrofit retrofit;
    private static APIService apiService;

    public static APIService getInstance() {
        if (retrofit == null) {
            retrofit = SingletonRetrofitClass.INSTANCE;
            apiService = retrofit.create(APIService.class);
        }

        return apiService;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.interceptors().add(getInterceptor());
        okhttpBuilder.interceptors().add(getLoggingInterceptor());
        return okhttpBuilder.build();
    }


    //통신 중 일어나는 log 확인 (request 값 or response 값 로그로 확인 가능)
    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


    //API에 자동으로 Header 설정하기 위한 Interceptor
    //여기선 header의 타입만 추가
    private static Interceptor getInterceptor() {
        return new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request(); //현재 request 가져오기

                Headers headers = new Headers.Builder()
                        .add("Content-Type", "application/json;charset=utf-8")
                        .build();

                //생성한 헤더를 갖는 request를 새롭게 생성
                Request newRequest = request.newBuilder().headers(headers).build();
                return chain.proceed(newRequest);
            }
        };
    }

    private static class SingletonRetrofitClass {
        private static final Retrofit INSTANCE = new Retrofit.Builder()
                .baseUrl("https://solved.ac/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }
}
