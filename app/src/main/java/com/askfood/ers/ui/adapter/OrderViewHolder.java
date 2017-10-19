package com.askfood.ers.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askfood.ers.R;
import com.askfood.ers.daocore.db.entity.Vo.OrderVo;
import com.askfood.ers.utils.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/18.
 */

public class OrderViewHolder  extends BaseViewHolder {

    @BindView(R.id.tv_order_number)
    TextView  tv_order_number;

    @BindView(R.id.tv_order_clientnum)
    TextView  tv_order_clientnum;
    @BindView(R.id.tv_order_time)
    TextView  tv_order_time;
    @BindView(R.id.tv_order_money)
    TextView  tv_order_money;
    @BindView(R.id.tv_order_status)
    TextView  tv_order_status;
    @BindView(R.id.tv_order_sort)
    TextView tv_order_sort;
    @BindView(R.id.iv_order_grab)
    ImageView  iv_order_grab;


    public OrderViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public OrderViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_order, parent, false));
    }

    public   void   bind(int  sort, boolean  viewgrab, OrderVo  orderVo){
        tv_order_sort.setText(String.valueOf(sort));
        iv_order_grab.setVisibility(viewgrab?View.VISIBLE:View.GONE);
        tv_order_clientnum.setText(StringUtils.nullStrToEmpty(orderVo.kh_code));
        tv_order_money.setText(StringUtils.DoubleToStr(orderVo.dhje)+"元");
        tv_order_status.setText("待抢");
        tv_order_time.setText(StringUtils.nullStrToEmpty(orderVo.dtime));
        tv_order_number.setText(StringUtils.nullStrToEmpty(orderVo.djid));
    }
}
