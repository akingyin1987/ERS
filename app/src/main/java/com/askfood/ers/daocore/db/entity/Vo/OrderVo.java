package com.askfood.ers.daocore.db.entity.Vo;

import java.io.Serializable;

/**
 * 订单信息
 * Created by Administrator on 2017/10/18.
 */

public class OrderVo  implements Serializable {


    private static final long serialVersionUID = 4327351617589075726L;

    public OrderVo() {
    }

    public OrderVo(String djid, String kh_code, String dtime, int state, Double dhje) {
        this.djid = djid;
        this.kh_code = kh_code;
        this.dtime = dtime;
        this.state = state;
        this.dhje = dhje;
    }

    public   String   djid;//订单编号

    public   String   kh_code;//客户编号

    public   String    dtime;//生成时间

    public   int   state;//订单状态

    public   Double   dhje;//订货金额


}
