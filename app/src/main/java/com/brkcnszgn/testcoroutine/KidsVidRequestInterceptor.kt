package com.brkcnszgn.testcoroutine

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class KidsVidRequestInterceptor : Interceptor {

    val CONNECT_TIMEOUT = "CONNECT_TIMEOUT"
    val READ_TIMEOUT = "READ_TIMEOUT"
    val WRITE_TIMEOUT = "WRITE_TIMEOUT"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = chain.request().newBuilder()


        var connectTimeout = chain.connectTimeoutMillis() / 1000
        var readTimeout = chain.readTimeoutMillis() / 1000
        var writeTimeout = chain.writeTimeoutMillis() / 1000

        val connectNew =
            original.header(CONNECT_TIMEOUT)
        val readNew =
            original.header(READ_TIMEOUT)
        val writeNew =
            original.header(WRITE_TIMEOUT)

        if (!TextUtils.isEmpty(connectNew)) {
            connectTimeout = Integer.valueOf(connectNew)
        }
        if (!TextUtils.isEmpty(readNew)) {
            readTimeout = Integer.valueOf(readNew)
        }
        if (!TextUtils.isEmpty(writeNew)) {
            writeTimeout = Integer.valueOf(writeNew)
        }

        builder.removeHeader(CONNECT_TIMEOUT)
        builder.removeHeader(READ_TIMEOUT)
        builder.removeHeader(WRITE_TIMEOUT)

        return chain
            .withConnectTimeout(connectTimeout, TimeUnit.SECONDS)
            .withReadTimeout(readTimeout, TimeUnit.SECONDS)
            .withWriteTimeout(writeTimeout, TimeUnit.SECONDS)
            .proceed(
                builder.method(original.method, original.body)
                    .build()
            )

    }
}