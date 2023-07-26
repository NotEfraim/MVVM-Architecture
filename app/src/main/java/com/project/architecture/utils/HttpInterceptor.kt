package com.project.architecture.utils

import android.content.SharedPreferences
import android.util.Log
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HttpInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = sharedPreferences.getString("token", "")
        var request = chain.request()

        request = request.newBuilder()
            .url(newUrl(request))
            .header("Authorization", "$token")
            .header("Content-Type", "application/json;charset=UTF-8")
            .build()

        val response = chain.proceed(request)

        Log.d(
            "InterceptorResponse: ",
            "Endpoint: ${request.url} \nCode: ${response.code} \nResponse: ${response.peekBody(2048).string()}")

        return response
    }

    private fun newUrl(request: Request): HttpUrl {
        //todo base url is from native
        val host = "https://dummyjson.com/"
        return request.url
            .newBuilder()
            .host(host.toHttpUrlOrNull()?.host?:"")
            .build()
    }
}