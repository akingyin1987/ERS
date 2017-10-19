package com.askfood.ers.presenter.impl;

import com.askfood.ers.api.EncasementApi;
import com.askfood.ers.base.rx.RxUtil;
import com.askfood.ers.daocore.db.entity.Vo.OrderVo;
import com.askfood.ers.net.RetrofitUtils;
import com.askfood.ers.net.config.CommonConstants;
import com.askfood.ers.net.mode.ApiListResult;
import com.askfood.ers.net.mode.ApiResult;
import com.askfood.ers.net.okhttp.OkHttpUtils;
import com.askfood.ers.presenter.GrabListContract;
import com.askfood.ers.utils.DateUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * 抢单例表
 * Created by Administrator on 2017/10/18.
 */

public class GrabListPresenterImpl  implements GrabListContract.Presenter{

    GrabListContract.View   mView;

    @Inject
    public GrabListPresenterImpl() {
    }

    @Override
    public void attachView(GrabListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void initialization() {

    }

    @Override
    public void getGrabListDatas() {
        mView.startRefresh();
        EncasementApi   encasementApi = RetrofitUtils.createApi(EncasementApi.class, OkHttpUtils.getInstance(), CommonConstants.API_HOST);
        encasementApi.getOrderGrabList(CommonConstants.SESSIONKEY).compose(RxUtil.<ApiListResult<OrderVo>>IO_Main())
                .doOnSubscribe(mView.getDoConsumer())
                .subscribe(new Consumer<ApiListResult<OrderVo>>() {
                    @Override
                    public void accept(ApiListResult<OrderVo> orderVoApiListResult) throws Exception {
                      mView.addGrabListDatas(orderVoApiListResult.getData());
                      mView.stopRefresh();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError("获取数据失败");
                        Random  random = new Random();
                        List<OrderVo>  orderVos = new LinkedList<>();
                        for(int i=0;i<10;i++){
                            orderVos.add(new OrderVo("djid"+random.nextInt(1000),""+random.nextInt(1000), DateUtil.getNowTimeString(),random.nextInt(3),random.nextDouble()));
                        }
                        mView.addGrabListDatas(orderVos);
                        mView.stopRefresh();
                    }
                });
    }

    Disposable   disposable;
    @Override
    public void configGrab(final OrderVo  orderVo, String userId) {

        EncasementApi   encasementApi = RetrofitUtils.createApi(EncasementApi.class, OkHttpUtils.getInstance(), CommonConstants.API_HOST);
      disposable =  encasementApi.configGrab(CommonConstants.SESSIONKEY,userId,orderVo.djid).compose(RxUtil.<ApiResult<String>>IO_Main())
                .doOnSubscribe(mView.getDoConsumer())
                .subscribe(new Consumer<ApiResult<String>>() {
                    @Override
                    public void accept(ApiResult<String> stringApiResult) throws Exception {
                        mView.showSucces("抢单成功");
                        mView.goDoOrder(orderVo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showError("获取数据失败");
                        mView.hideLoadDialog();
                        mView.goDoOrder(orderVo);
                    }
                });
    }

    @Override
    public void cancelRequest() {
        if(null != disposable && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
