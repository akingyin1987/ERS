package com.askfood.ers.daocore.db.entity.Vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/16.
 */

public class BillVo  implements Serializable {

    public    int  number;

    public    String   goodsName;

    public    String    unit;

    public BillVo() {
    }

    public BillVo(int number, String goodsName, String unit) {
        this.number = number;
        this.goodsName = goodsName;
        this.unit = unit;
    }
}
