package com.example.lamp


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LampAPI {
    //https://jsonplaceholder.typicode.com/posts
    @GET("lamps")
    fun getLamps(): Call<List<Lamp>>

    @PUT("lamps/{id}")
    fun putLamp(@Path("id") lampId: String, @Body lamp: Lamp): Call<ResponseBody>

    companion object {
        private const val SERVER_URL = "http://10.0.2.2:5000/"

        fun newInstance(baseURL: String = SERVER_URL): LampAPI {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()

            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(baseURL)
                .build()
                .create(LampAPI::class.java)
        }
    }
}