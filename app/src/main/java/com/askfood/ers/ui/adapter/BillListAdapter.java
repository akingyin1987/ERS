package com.askfood.ers.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.askfood.ers.daocore.db.entity.Vo.BillVo;
import com.askfood.ers.injection.scope.ActivityContext;
import com.chad.library.adapter.base.BaseQuickAdapter;



import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BillListAdapter  extends BaseQuickAdapter<BillVo,BillViewHolder>{
    LayoutInflater  mInflater;
    @Inject
    public BillListAdapter(@ActivityContext Context  context) {
        super(null);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected BillViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return new BillViewHolder(mInflater,parent);
    }

    @Override
    protected void convert(BillViewHolder helper, BillVo item) {
        helper.bind(item);
    }
}
