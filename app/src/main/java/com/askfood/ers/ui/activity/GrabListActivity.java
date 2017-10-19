package com.askfood.ers.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.dialog.DialogCallBack;
import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.daocore.db.entity.Vo.OrderVo;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.GrabListContract;
import com.askfood.ers.presenter.impl.GrabListPresenterImpl;
import com.askfood.ers.ui.adapter.OrderListAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 抢单例表
 * Created by Administrator on 2017/10/18.
 */

public class GrabListActivity extends AbsBaseActivity<GrabListPresenterImpl> implements GrabListContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_client_name)
    TextView tvClientName;
    @BindView(R.id.re_bluetooth)
    RecyclerView reBluetooth;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Inject
    OrderListAdapter   adapter;

    @Override
    public void startRefresh() {
         swipe.setRefreshing(true);
    }

    @Override
    public void stopRefresh() {
        swipe.setRefreshing(false);
    }

    @Override
    public void addGrabListDatas(List<OrderVo> orderVos) {
        adapter.setNewData(orderVos);
    }

    @Override
    protected void initEventAndData() {
        reBluetooth.setLayoutManager(new LinearLayoutManager(this));
        reBluetooth.setItemAnimator(new DefaultItemAnimator());
        adapter.bindToRecyclerView(reBluetooth);
        adapter.setViewgrab(true);
        reBluetooth.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                if(swipe.isRefreshing()){
                    showWarning("当前正在获取数据中，请等待");
                    return;
                }
                showConfigGrab(position,adapter.getItem(position));
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pd.setCanceledOnTouchOutside(true);
                mPresenter.getGrabListDatas();
            }
        });
        pd.setCanceledOnTouchOutside(true);
        mPresenter.getGrabListDatas();
    }


    @Override
    public void showConfigGrab(int postion, final OrderVo orderVo) {
        DialogUtil.showConfigDialog(this, "确认要抢：" + orderVo.djid + " ,确认后将无法取消!", new DialogCallBack<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if(aBoolean){
                    pd.setCanceledOnTouchOutside(false);
                    pd.setCancelable(false);
                    mPresenter.configGrab(orderVo,"123");
                }
            }
        });
    }

   @Override
    protected int getContentViewID() {
        return R.layout.activity_grablist;
    }

    @Override
    protected void injectDagger() {
        ButterKnife.bind(this);
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(ERSApp.get().getApplicationComponent()).build();
        activityComponent.inject(this);
    }

    @Override
    protected void initToolBar() {
       setToolBar(toolbar,"待装订单列表");
    }

    @Override
    public Consumer<Disposable> getDoConsumer() {
        return doOnConsmer;
    }



    @OnClick(R.id.fab)
    public void onViewClicked() {
        if(swipe.isRefreshing()){
            showWarning("当前正在获取数据中，请等待");
            return;
        }
        pd.setCanceledOnTouchOutside(true);
        mPresenter.getGrabListDatas();
    }

    @Override
    public void goDoOrder(OrderVo orderVo) {
        Bundle  bundle = new Bundle();
        bundle.putSerializable("data",orderVo);
        readyGo(GrabBillActivity.class,bundle);
    }
}
