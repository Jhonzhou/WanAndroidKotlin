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
    fun getHomePageList(@Path("page") page: Int): Call<ResponseResult<PageListResponse>>


    /**
     * 搜索
     */
    @POST(HttpConstants.SEARCH)
    fun search(@Path("page") page: Int, @Query("k") key: String): Call<ResponseResult<PageListResponse>>

    /**
     * 项目分类
     */
    @GET(HttpConstants.PROJECT_TAB)
    fun getProjectTabList(): Call<ResponseResult<List<TagResponseBean>>>

    /**
     * 项目分类列表详情
     */
    @GET(HttpConstants.PROJECT_TAB_DETAIL_LIST)
    fun getProjectDetailList(@Path("page") page: Int, @Query("cid") cid: Int): Call<ResponseResult<PageListResponse>>

    /**
     * 体系数据
     */
    @GET(HttpConstants.TREE_TAG)
    fun getTreeTagList(): Call<ResponseResult<List<TagResponseBean>>>

    /**
     * 项目分类列表详情
     */
    @GET(HttpConstants.TREE_DETAIL_LIST)
    fun getTreeDetailList(@Path("page") page: Int, @Query("cid") cid: Int): Call<ResponseResult<PageListResponse>>

    /**
     * 导航数据
     */
    @GET(HttpConstants.NAVIGATION_LIST)
    fun getNavigationList(): Call<ResponseResult<List<NavigationResponseBean>>>

    /**
     * 公众号类别
     */
    @GET(HttpConstants.TENCENT_LIST)
    fun getTencentList(): Call<ResponseResult<List<TagResponseBean>>>

    /**
     * 公众号详情列表
     */
    @GET(HttpConstants.TENCENT_DETAIL_LIST)
    fun getTencentDetailList(@Path("page") page: Int, @Path("cid") cid: Int): Call<ResponseResult<PageListResponse>>

    /**
     * 问答列表
     */
    @GET(HttpConstants.ANSWER_LIST)
    fun getAnswerList(@Path("page") page: Int): Call<ResponseResult<PageListResponse>>
}