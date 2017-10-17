package com.askfood.ers.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.askfood.ers.ERSApp;
import com.askfood.ers.R;
import com.askfood.ers.base.AbsBaseActivity;
import com.askfood.ers.base.dialog.DialogCallBack;
import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.daocore.db.entity.Vo.BillVo;
import com.askfood.ers.injection.component.ActivityComponent;
import com.askfood.ers.injection.component.DaggerActivityComponent;
import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.presenter.BillModifyContract;
import com.askfood.ers.presenter.impl.BillModifyPresenterImpl;
import com.askfood.ers.ui.adapter.BillListAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 单据修改
 * Created by Administrator on 2017/10/16.
 */


public class BillModifyActivity extends AbsBaseActivity<BillModifyPresenterImpl> implements BillModifyContract.View {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_bill_number)
    TextView tvBillNumber;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.btn_config)
    Button btnConfig;

    @Inject
    BillListAdapter  adapter;
    @Override
    protected void initEventAndData() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
        List<BillVo>  billVos = new LinkedList<>();
        Random  random = new Random();
        for(int i=0;i<10;i++){
            billVos.add(new BillVo(random.nextInt(100),"商品"+i,i%2==0?"件":"包"));
        }
        adapter.setNewData(billVos);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                final BillVo  billVo = adapter.getItem(position);
                DialogUtil.modifyFoodInfoDialog(BillModifyActivity.this, "商品：" + billVo.goodsName, billVo.number, new DialogCallBack<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if(null != integer){
                            billVo.number = integer.intValue();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_bill_modify;
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
     setToolBar(toolbar,"单据修改");
    }


    @Override
    public void onBackPressed() {
        onViewClicked();
    }

    @OnClick(R.id.btn_config)
    public void onViewClicked() {
        DialogUtil.showConfigDialog(this, "确定单据修改完成!", new DialogCallBack<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                finish();
            }
        });
    }
}
