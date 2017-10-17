package com.askfood.ers.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askfood.ers.R;
import com.askfood.ers.daocore.db.entity.Vo.BillVo;
import com.askfood.ers.utils.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BillViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_food_name)
    TextView tvFoodName;
    @BindView(R.id.tv_food_number)
    TextView tvFoodNumber;
    @BindView(R.id.tv_food_unit)
    TextView tvFoodUnit;

    public BillViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public BillViewHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_bill, parent, false));
    }

    public   void   bind(BillVo   billVo){
        tvFoodName.setText(StringUtils.nullStrToEmpty(billVo.goodsName));
        tvFoodNumber.setText(StringUtils.nullStrToEmpty(billVo.number));
        tvFoodUnit.setText(StringUtils.nullStrToEmpty(billVo.unit));
    }

}
