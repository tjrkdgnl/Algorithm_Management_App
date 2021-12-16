package com.ama.algorithmmanagement.Network

import com.ama.algorithmmanagement.fake.FakeSharedPreference
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object KAPIGenerator {
    private lateinit var retrofit: Retrofit
    private val apiService: KAPIService by lazy {
        retrofit.create(KAPIService::class.java)
    }

    fun getInstance(): KAPIService {
        if (!this::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://solved.ac/api/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }
        return apiService
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            interceptors().add(getInterceptor())
            interceptors().add(getLoggingInterceptor())
        }.build()
    }


    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //API에 자동으로 Header 설정하기 위한 Interceptor
    //여기선 header의 타입만 추가
    private fun getInterceptor(): Interceptor {
//        Timber.e("초기화")
//        val solvedacToken = with(FakeSharedPreference()) {
//            setSolvedacToken("solvedacToken=s:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJoYW5kbGUiOiJza2poMDgxOCIsImlhdCI6MTYzOTEwMzM4Mn0.9zljB89UD-Au0pcypQgJ6sBk4YPntVo_FUXQYjJKBtc.h2ItJm2b4l29UH695OJdAGWcCjq6kCJ5xb+/OBmjnpc")
//            getSolvedacToken()
//        }
        return Interceptor { chain ->
            val request = chain.request()
            val headers = Headers.Builder().apply {
                add("Content-Type", "application/json;charset=utf-8")


//                solvedacToken?.let { token ->
//                    add("Cookie", token)
//                }
            }.build()

            //생성한 헤더를 갖는 request를 새롭게 생성
            val newReqeust = request.newBuilder().headers(headers).build()

            //interceptor 리턴
            chain.proceed(newReqeust)
        }
    }
}