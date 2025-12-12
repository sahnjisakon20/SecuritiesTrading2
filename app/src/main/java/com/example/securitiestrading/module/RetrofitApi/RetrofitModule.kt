package com.example.securitiestrading.module.RetrofitApi

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.apply
import kotlin.jvm.java

@Module
object RetrofitModule: KoinComponent {
    const val MAX_REQUESTS_PER_HOST: Int = 10

    private lateinit var okHttpClient: OkHttpClient

    @Single
    fun getInstance(applicationContext: Context): IRetrofitApi {
        return initApi(applicationContext)
    }

    private fun initApi(context: Context): IRetrofitApi {
        val TIMEOUT_IN_SECS = 30L
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okhttpClientBuilder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_IN_SECS, TimeUnit.SECONDS)
            .addInterceptor(logging)


        okHttpClient = okhttpClientBuilder.build()
        okHttpClient.dispatcher.maxRequestsPerHost = MAX_REQUESTS_PER_HOST

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.twse.com.tw/v1/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RetrofitFlowFactory.create())
            .build()

        return retrofit.create(IRetrofitApi::class.java)
    }
}