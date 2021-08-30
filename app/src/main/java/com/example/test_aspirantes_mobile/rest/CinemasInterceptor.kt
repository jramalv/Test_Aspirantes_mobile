package com.example.test_aspirantes_mobile.rest

import android.content.Context
import androidx.annotation.NonNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class CinemasInterceptor internal constructor(private val ctx: Context) : Interceptor {
    private var loginService: LoginService? = null

    @NonNull
    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = authorizationHelper.getAccessToken()?.let {
            original.newBuilder()
                .addHeader("Authorization", it)
                .build()
        }
        return chain.proceed(request!!)
    }

    private val authorizationHelper: LoginService
        get() {
            if (loginService == null) {
                loginService = LoginService(ctx)
            }
            return loginService as LoginService
        }
}