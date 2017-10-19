/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.net;



import com.askfood.ers.net.retrofitConverter.FastJsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


/**
 * Created by zlcd on 2016/2/15.
 */
public class RetrofitUtils {

    private static Retrofit singleton;



    private  static  final  String   baseUrl="http://gank.io/api/";

    public static final String BAIDU_IMAGES_URLS = "http://image.baidu.com";


    /**
     * 转JSON
     * @param clazz
     * @param okHttpClient
     * @param baseUrl
     * @param <T>
     * @return
     */
    public static <T> T createApi(Class<T> clazz, OkHttpClient okHttpClient, String baseUrl) {
        synchronized (RetrofitUtils.class) {
                Retrofit.Builder builder = new Retrofit.Builder();
                builder.baseUrl(baseUrl);//设置远程地址
                builder.addConverterFactory(FastJsonConverterFactory.create());
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                builder.client(okHttpClient);
                return builder.build().create(clazz);
        }
    }

    /**
     * 转XML
     * @param baseUrl
     * @param okHttpClient
     * @param service
     * @param <T>
     * @return
     */
    public static  <T> T createByXML(String baseUrl,OkHttpClient okHttpClient, Class<T> service){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

}
