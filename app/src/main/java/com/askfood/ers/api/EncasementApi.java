package com.askfood.ers.api;

import com.askfood.ers.api.model.UserLoginModel;
import com.askfood.ers.daocore.db.entity.Vo.OrderVo;
import com.askfood.ers.net.mode.ApiListResult;
import com.askfood.ers.net.mode.ApiResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 装箱接口
 * Created by Administrator on 2017/10/18.
 */

public interface EncasementApi {

    /**
     *  登录
      * @param account
     * @param password
     * @return
     */
     @FormUrlEncoded
     @POST("login")
     Observable<ApiResult<UserLoginModel>>   login(@Field("account")String  account,@Field("password")String  password);


    /**
     * 获取所有抢单信息
     * @param sessionkey
     * @return
     */
    @FormUrlEncoded
    @POST("QueryDDzb")
    Observable<ApiListResult<OrderVo>>     getOrderGrabList(@Field("sessionkey")String sessionkey);


    /**
     * 确认抢单
     * @param sessionkey
     * @param userId
     * @param djid
     * @return
     */
    @FormUrlEncoded
    @POST("SetQDR")
    Observable<ApiResult<String>>   configGrab(@Field("sessionkey")String sessionkey,
                                               @Field("userId")String  userId,@Field("djid ")String djid );



}
