package com.askfood.ers.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.askfood.ers.daocore.db.entity.Vo.OrderVo;
import com.askfood.ers.injection.scope.ActivityContext;
import com.chad.library.adapter.base.BaseQuickAdapter;



import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/18.
 */

public class OrderListAdapter  extends BaseQuickAdapter<OrderVo,OrderViewHolder> {

   private boolean   viewgrab;

    public boolean isViewgrab() {
        return viewgrab;
    }

    public OrderListAdapter setViewgrab(boolean viewgrab) {
        this.viewgrab = viewgrab;
        return this;
    }

    LayoutInflater  mInflater;
    @Inject
    public OrderListAdapter(@ActivityContext Context  context) {
        super(null);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected OrderViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(mInflater,parent);
    }

    @Override
    protected void convert(OrderViewHolder helper, OrderVo item) {
       helper.bind(helper.getLayoutPosition()+1,viewgrab,item);
    }
}
