package com.example.quiz_app.api

import com.example.quiz_app.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(5 , TimeUnit.MINUTES)
            .readTimeout(5 ,TimeUnit.MINUTES)
            .writeTimeout(5,TimeUnit.MINUTES)
            .addInterceptor(logging).build()




        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val api by lazy {
        retrofit.create(QuizApi::class.java)
    }
}