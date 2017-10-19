package com.askfood.ers.presenter;

import com.askfood.ers.base.BasePresenter;
import com.askfood.ers.base.BaseView;
import com.askfood.ers.daocore.db.entity.Vo.OrderVo;

import org.simpleframework.xml.Order;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 抢单例表
 * Created by Administrator on 2017/10/18.
 */

public interface GrabListContract {

    interface View extends BaseView {

        void    startRefresh();


        Consumer<Disposable> getDoConsumer();

        void    stopRefresh();


        void    showConfigGrab(int  postion,OrderVo  orderVo);


        /**
         * 添加抢单数据展示
         * @param orderVos
         */
        void    addGrabListDatas(List<OrderVo>  orderVos);

        /**
         * 进入单据处理
         */
        void    goDoOrder(OrderVo  orderVo);
    }

    interface Presenter extends BasePresenter<GrabListContract.View> {

        /**
         * 获取抢单数据
         */
        void    getGrabListDatas();


        /**
         * 确认抢单
         */
        void    configGrab(OrderVo  orderVo,String  userId);


        void    cancelRequest();

    }
}
