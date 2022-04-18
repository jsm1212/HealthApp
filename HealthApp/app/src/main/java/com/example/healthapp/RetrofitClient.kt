package com.example.healthapp

import android.os.StrictMode
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object{
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit?{
            if(instance == null) {
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                // Gson은 Java 객체를 JSON으로 변환
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                instance = Retrofit
                    .Builder()
                    .baseUrl("http://192.168.219.100:3000/") // 본인 IP주소로 입력하기!!
                    .addConverterFactory(ScalarsConverterFactory.create()) // 문자열 리턴받는경우
                    .addConverterFactory(GsonConverterFactory.create(gson)) // object, integer
                    .build()
            }
            return instance!!
        }
    }
}