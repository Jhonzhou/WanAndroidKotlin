package com.bee.wanandroidkotlin.http

import com.bee.wanandroidkotlin.constants.HttpConstants
import com.bee.wanandroidkotlin.http.beans.HomeBannerResponse
import com.bee.wanandroidkotlin.http.beans.LoginResponseBean
import com.bee.wanandroidkotlin.http.beans.ResponseResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/23
 * @Description:
 */
interface WanAndroidService {
    /**
     * 登录接口
     */
    @POST(HttpConstants.LOGIN)
    @FormUrlEncoded
    fun login(@Field("username") username: String,
              @Field("password") password: String
    ): Call<ResponseResult<LoginResponseBean>>

    /**
     * 注册接口
     */
    @POST(HttpConstants.REGISTER)
    @FormUrlEncoded
    fun register(@Field("username") username: String,
                 @Field("password") password: String,
                 @Field("repassword") rePassword: String
    ): Call<ResponseResult<LoginResponseBean>>

    @GET(HttpConstants.HOME_BANNER)
    fun getHomeBanner(): Call<ResponseResult<List<HomeBannerResponse>>>
}