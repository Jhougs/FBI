package com.example.nasaapi.server

import com.example.nasaapi.ui.model.FBI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("wanted/v1/list")
    fun getTopRated(@Query("api_key") apiKey:String): Call<FBI>

    companion object{
        val URL_API= "https://api.fbi.gov/"
        fun create(): ApiService{
            val interceptor= HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }
}