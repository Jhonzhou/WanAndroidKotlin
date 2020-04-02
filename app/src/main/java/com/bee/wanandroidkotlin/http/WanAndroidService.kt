package com.bee.wanandroidkotlin.http

import com.bee.wanandroidkotlin.constants.HttpConstants
import com.bee.wanandroidkotlin.http.beans.*
import retrofit2.Call
import retrofit2.http.*

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

    /**
     * 首页banner
     */
    @GET(HttpConstants.HOME_BANNER)
    fun getHomeBanner(): Call<ResponseResult<List<HomeBannerResponse>>>

    /**
     * 首页文章列表
     */
    @GET(HttpConstants.TOP_LIST)
    fun getTopList(): Call<ResponseResult<ArrayList<ArticleListResponseData>>>

    /**
     * 首页文章列表
     */
    @GET(HttpConstants.HOME_LIST)
    fun getHomePageList(@Path("page") page: Int): Call<ResponseResult<ArticlePageListResponse>>

    /**
     * 搜索
     */
    @POST(HttpConstants.SEARCH)
    fun search(@Path("page") page: Int, @Query("k") key: String): Call<ResponseResult<ArticlePageListResponse>>
}