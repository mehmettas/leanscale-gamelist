package com.app.gamelist.data.remote.service

import android.os.Build
import com.app.gamelist.CoreApp
import com.app.gamelist.utils.AppConstants.API_URL
import com.app.gamelist.utils.AppConstants.REQUEST_TIMEOUT
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.*
import java.util.*
import java.util.concurrent.TimeUnit

object ServiceClient {
    private var retrofit: Retrofit? = null
    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val cacheDir = File(CoreApp.context.cacheDir, UUID.randomUUID().toString())
    // 50 MiB cache
    private val cache = Cache(cacheDir, 50 * 1024 * 1024)

    // Create a Custom Interceptor to apply Headers application wide
    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request().newBuilder()

        chain.proceed(request.build())
    }

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)


    private fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .client(okHttp.build())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }
        return retrofit!!
    }

    fun createNetworkClient() = getClient()

    inline fun <reified T> createWebService(): T {
        return createNetworkClient().create(T::class.java)
    }

    fun getByteStream(paramResponse: ResponseBody): InputStream {
        return paramResponse.byteStream()
    }

    fun getStringFromByte(paramInputStream: InputStream): String {
        val localStringBuilder = StringBuilder()
        val localBufferedReader = BufferedReader(InputStreamReader(paramInputStream))
        try {
            while (true) {
                val str = localBufferedReader.readLine() ?: break
                localStringBuilder.append(str)
            }
        } catch (localIOException: IOException) {
        }

        return localStringBuilder.toString()
    }
}