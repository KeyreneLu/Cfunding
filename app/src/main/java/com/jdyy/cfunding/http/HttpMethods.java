package com.jdyy.cfunding.http;

import com.jdyy.cfunding.bean.Banner;
import com.jdyy.cfunding.bean.Information;
import com.jdyy.cfunding.bean.Item;
import com.jdyy.cfunding.bean.Product;
import com.jdyy.cfunding.bean.Token;
import com.jdyy.cfunding.http.Api.ApiService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/6 0006.
 */
public class HttpMethods {

    public static final String BASE_URL = "http://t.zamamall.cn/Api/";
    public static final int DEFAULT_TIME = 5;

    private Retrofit mRetrofit;

    private ApiService mApiService;

    private HttpMethods() {
//        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
//                .cookieJar(new CookieManger(context))
//                .addInterceptor(loginInterceptor)
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .build();

//        httpClientBuilder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    private static class singletonHodler {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return singletonHodler.INSTANCE;
    }

    public void getAdBanner(Subscriber<List<Banner>> subscriber) {
        mApiService.getHomeBanner()
                .map(new HttpResultFun<List<Banner>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getHotItem(Subscriber<List<Item>> subscriber, String appId, String appSecret) {
        mApiService.getUserToken(appId, appSecret)
                .flatMap(new Func1<Token, Observable<HttpResult<List<Item>>>>() {
                    @Override
                    public Observable<HttpResult<List<Item>>> call(Token token) {
                        if (!token.getCode().equals("00")) {
                            throw new ApiException(token.getCode());
                        }
                        GlobalToken.updateToken(token.getResult().getToken());
                        return mApiService.getItemList(token.getResult().getToken());
                    }
                })
                .map(new HttpResultFun<List<Item>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getAllProduct(Subscriber<List<Product>> subscriber, String appId, String appSecret, final String page, final int cId) {
        mApiService.getUserToken(appId, appSecret)
                .flatMap(new Func1<Token, Observable<HttpPage<List<Product>>>>() {
                    @Override
                    public Observable<HttpPage<List<Product>>> call(Token token) {
                        if (!token.getCode().equals("00")) {
                            throw new ApiException(token.getCode());
                        }
                        GlobalToken.updateToken(token.getResult().getToken());
                        if (cId == 0) {
                            return mApiService.getProductList(token.getResult().getToken(), page);
                        } else {
                            return mApiService.getTypeList(token.getResult().getToken(), page, cId);
                        }
                    }
                })
                .map(new HttpResultFun2<List<Product>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getPrejectInformation(Subscriber<Information> subscriber, String appId, String appSecret, final String id) {
        mApiService.getUserToken(appId, appSecret)
                .flatMap(new Func1<Token, Observable<HttpResult<Information>>>() {
                    @Override
                    public Observable<HttpResult<Information>> call(Token token) {
                        if (!token.getCode().equals("00")) {
                            throw new ApiException(token.getCode());
                        }
//                        GlobalToken.updateToken(token.getResult().getToken());
//                        SPUtils.put(context,"token",token.getResult().getToken());
                        return mApiService.getProjactDetail(token.getResult().getToken(), id);
                    }
                })
                .map(new HttpResultFun<Information>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getPrejectInformationNoToken(Subscriber<Information> subscriber, String token, String id) {
        mApiService.getProjactDetail(token, id)
                .map(new HttpResultFun<Information>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void login(Subscriber<String> subscriber, String appId, String appSecret, final String username, final String password) {
        mApiService.getUserToken(appId, appSecret)
                .flatMap(new Func1<Token, Observable<HttpResult<String>>>() {
                    @Override
                    public Observable<HttpResult<String>> call(Token token) {
                        if (!token.getCode().equals("00")) {
                            throw new ApiException(token.getCode());
                        }
                        GlobalToken.updateToken(token.getResult().getToken());
                       return mApiService.userLogin(token.getResult().getToken(),username,password);
                    }
                })
                .map(new HttpResultFun<String>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    //统一处理数据的
    private class HttpResultFun<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (!tHttpResult.getCode().equals("00")) {
                throw new ApiException(tHttpResult.getCode());
            }
            return tHttpResult.getResult();
        }
    }

    //处理分页数据的
    private class HttpResultFun2<T> implements Func1<HttpPage<T>, T> {
        @Override
        public T call(HttpPage<T> tHttpPage) {
//            Log.e(Constant.MAIN_LOG, tHttpPage.getPageTotal() + "");
            if (!tHttpPage.getCode().equals("00")) {
                throw new ApiException(tHttpPage.getCode());
            }
            return tHttpPage.getResult();
        }
    }
}
