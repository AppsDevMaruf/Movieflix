package com.maruf.movieflix.api

import com.maruf.movieflix.utils.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

/* var request = chain.request().newBuilder();
            var originalHttpUrl = chain.request().url();
            var url = originalHttpUrl.newBuilder()
                  .addQueryParameter("key", "***your api key ***").build();
            request.url(url);
            return chain.proceed(request.build());*/

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api_key", API_KEY).build())
            .build()
        return chain.proceed(request)
    }


}

