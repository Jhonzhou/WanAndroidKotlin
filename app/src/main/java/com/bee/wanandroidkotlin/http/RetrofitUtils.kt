package com.bee.wanandroidkotlin.http

import android.text.TextUtils
import android.util.Log
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.BuildConfig
import com.bee.wanandroidkotlin.constants.HttpConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/23
 * @Description:
 */
object RetrofitUtils {
    private const val CONNECTION_TIME_OUT: Long = 30L
    private const val READ_TIME_OUT: Long = 10L
    private const val WRITE_TIME_OUT: Long = 30L

    private const val TAG = "Retrofit"
    private const val CONTENT_PRE = "OkHttp: "
    private const val SET_COOKIE_KEY = "set-cookie"
    private const val COOKIE_NAME = "Cookie"

    fun <T> getService(service: Class<T>): T {
        return getRetrofit(HttpConstants.BASE_URL).create(service)
    }

    fun <T> getService(url: String, service: Class<T>): T {
        return getRetrofit(url).create(service)
    }

    private fun getRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .client(initOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun initOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient()
                .newBuilder().apply {
                    connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                    readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    addInterceptor {
                        val request = it.request()
                        val response = it.proceed(request)
                        val requestUrl = request.url().toString()
                        val host = request.url().host()
                        if ((requestUrl.contains(HttpConstants.LOGIN)
                                        || requestUrl.contains(HttpConstants.REGISTER)) &&
                                response.headers(SET_COOKIE_KEY).isNotEmpty()) {
                            val cookies = response.headers(SET_COOKIE_KEY)
                            val encodeCookie = encodeCookie(cookies)
                            saveCookie(encodeCookie, requestUrl, host)
                        }else if (requestUrl.contains(HttpConstants.LOGOUT)){

                        }
                        response
                    }
                    addInterceptor {
                        val request = it.request()
                        val requestBuilder = request.newBuilder()
                        val host = request.url().host()
                        if (host.isNotEmpty()) {
                            val hostCookie: String by Preference(host, "")
                            if (hostCookie.isNotEmpty()) {
                                requestBuilder.addHeader(COOKIE_NAME, hostCookie)
                            }
                        }
                        if (TextUtils.isEmpty(request.header("Content-Type"))) {
                            requestBuilder.addHeader("Content-Type", "application/json;charset=utf-8")
                        }
                        it.proceed(requestBuilder.build())
                    }
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                            Log.i(TAG, CONTENT_PRE + it)
                        }).apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    }
                    hostnameVerifier { _, _ -> true }

                }
        return okHttpClientBuilder.build()
    }

    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    private fun saveCookie(cookie: String?, url: String?, host: String?) {
        cookie ?: return
        url ?: return
        var urlCookie: String by Preference(url, cookie)
        @Suppress("UNUSED_VALUE")
        urlCookie = cookie
        host ?: return
        var hostCookie: String by Preference(host, cookie)
        @Suppress("UNUSED_VALUE")
        hostCookie = cookie
    }

    /**
     * save cookie string
     */
    private fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
                .map { cookie ->
                    cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                }
                .forEach {
                    it.filterNot { set.contains(it) }.forEach { set.add(it) }
                }

        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }

        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }

        return sb.toString()
    }
}