/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.net.okhttp;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * OkHTTP 网络请求回调
 * Created by zlcd on 2015/12/29.
 */
public abstract class AbsAPICallback implements Callback {

    //请求错误
    public  abstract void   onFailure(int code,String  message);

    //请求成功
    public  abstract  void  onSuccess(JSONObject   jsonObject);


    //是否验证信息
    private    boolean    iSintercept  = true;

    public boolean Valintercept(JSONObject   valJsonObject) {
        return iSintercept;
    }

    public void setiSintercept(boolean iSintercept) {
        this.iSintercept = iSintercept;
    }

    @Override
    public void onFailure(Call call, IOException e) {

        onFailure(0,"网络连接错误，请检查网络是否正常开启");
        if(null != call){
            call.cancel();
        }

    }

    @Override
    public void onResponse(Call call,Response response) throws IOException {
        try{
            int  httpcode = response.code();
            if(response.isSuccessful()){
                JSONObject    result = new JSONObject(response.body().string());
                if(iSintercept){
                    boolean   val = Valintercept(result);
                    if(!val){
                        return;
                    }
                }
                onSuccess(result);
            }else{
                String   errormsg = "连接服务异常，请稍后再试";
                if(httpcode == 408){
                    errormsg="请求超时，请检查网络或稍后再试";
                }else if(httpcode>=500){
                    errormsg="连接服务器失败";
                }
                onFailure(httpcode,errormsg);
            }
        }catch (Exception e){
            e.printStackTrace();
            onFailure(0,"处理数据异常");
        }
    }
}
