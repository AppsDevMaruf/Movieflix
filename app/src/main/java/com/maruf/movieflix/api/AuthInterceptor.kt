package com.maruf.movieflix.api

import com.maruf.movieflix.utils.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("accept", "application/json")
            .url(chain.request().url.newBuilder().addQueryParameter("api_key", API_KEY).build())
            .build()
        return chain.proceed(request)
    }


}

