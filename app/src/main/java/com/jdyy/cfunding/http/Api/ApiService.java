package com.jdyy.cfunding.http.Api;


import com.jdyy.cfunding.bean.Banner;
import com.jdyy.cfunding.bean.Information;
import com.jdyy.cfunding.bean.Item;
import com.jdyy.cfunding.bean.Product;
import com.jdyy.cfunding.bean.Token;
import com.jdyy.cfunding.http.HttpPage;
import com.jdyy.cfunding.http.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @Query 表示地址后面携带的参数 如：start = 1&count = 10
 * @Path 表示地址后跟的变量
 * @FormUrlEncoded  post以表单的方式上传
 * @POST("register")
 * Created by Administrator on 2016/9/8 0008.
 */
public interface ApiService {

    /**
     * @return 获取首页的banner
     */
    @GET("Ads/getBanner")
    Observable<HttpResult<List<Banner>>> getHomeBanner();

    /**
     * @param appId
     * @param appSecret
     * @return 请求数据所需的Token
     */
    @FormUrlEncoded
    @POST("Auth/getToken")
    Observable<Token> getUserToken(@Field("appId") String appId, @Field("appSecret") String appSecret);

    /**
     * @param token 请求数据所需的token
     * @return 获取首页热门项目
     */
    @FormUrlEncoded
    @POST("Item/getRecItemList")
    Observable<HttpResult<List<Item>>> getItemList(@Field("token") String token);

    /**
     * @param token 请求数据所需的token
     * @param page 页数
     * @return 获得所有产品的列表
     */
    @FormUrlEncoded
    @POST("Item/getItemList")
    Observable<HttpPage<List<Product>>> getProductList(@Field("token") String token,@Field("page") String page);

    /**
     * @param token 请求数据所需的token
     * @param page 页数
     * @param cid 分类id
     * @return 分类产品列表
     */
    @FormUrlEncoded
    @POST("Item/getItemList")
    Observable<HttpPage<List<Product>>> getTypeList(@Field("token") String token,@Field("page") String page,@Field("cid") int cid);

    @FormUrlEncoded
    @POST("Item/getItemInfo")
    Observable<HttpResult<Information>> getProjactDetail(@Field("token") String token,@Field("id") String id);

    /**
     *
     * @param token 请求数据所需token
     * @param username 用户名
     * @param password 用户密码
     * @return 登录状态
     */
    @FormUrlEncoded
    @POST("Public/login")
    Observable<HttpResult<String>> userLogin(@Field("token") String token,@Field("username") String username,@Field("password") String password);


}
