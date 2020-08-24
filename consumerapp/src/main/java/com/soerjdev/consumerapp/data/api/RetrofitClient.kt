package com.soerjdev.consumerapp.data.api

import com.google.gson.GsonBuilder
import com.soerjdev.consumerapp.utils.AUTH_TOKEN
import com.soerjdev.consumerapp.utils.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun httpClient(): OkHttpClient {

    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val builder = OkHttpClient.Builder()

    builder.readTimeout(10, TimeUnit.SECONDS)
    builder.connectTimeout(10, TimeUnit.SECONDS)
    builder.addInterceptor(logInterceptor)
    builder.addInterceptor(
        AuthInterceptor(
            AUTH_TOKEN
        )
    )

    return builder.build()

}

inline fun <reified T> apiRequest(okHttpClient: OkHttpClient): T {

    val gson = GsonBuilder().create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    return retrofit.create(T::class.java)

}


// Add Authorization Header
class AuthInterceptor(private var authToken: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        val requestHeader = req.newBuilder()
            .addHeader("Authorization", "token $authToken")
            .build()

        return chain.proceed(requestHeader)
    }
}