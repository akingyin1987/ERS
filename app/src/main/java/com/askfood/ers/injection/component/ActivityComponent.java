/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.injection.component;


import com.askfood.ers.injection.module.ActivityModule;
import com.askfood.ers.injection.scope.PerActivity;
import com.askfood.ers.ui.activity.BillModifyActivity;
import com.askfood.ers.ui.activity.CheckBusinessActilvity;
import com.askfood.ers.ui.activity.GrabBillActivity;
import com.askfood.ers.ui.activity.HomeActivity;
import com.askfood.ers.ui.activity.LoginActivity;
import com.askfood.ers.ui.activity.MainBusinessActivity;
import com.askfood.ers.ui.activity.PickingActivity;
import com.askfood.ers.ui.activity.PrintCodeBoxActivity;
import com.askfood.ers.ui.activity.RecheckMainActivity;
import com.askfood.ers.ui.activity.ScanBluetootActivity;
import com.askfood.ers.ui.activity.ScanCodeActivity;
import com.askfood.ers.ui.activity.WorkBoxCodeActivity;

import dagger.Component;

/**
 * 生命周期与Activity一起
 * Created by Anthony on 2016/6/13.
 * Class Note:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void  inject(LoginActivity   activity);

    void  inject(HomeActivity activity);

    void  inject(ScanBluetootActivity  activity);

    void  inject(MainBusinessActivity  activity);

    void  inject(GrabBillActivity activity);

    void  inject(WorkBoxCodeActivity  activity);

    void  inject(ScanCodeActivity  activity);

    void  inject(PrintCodeBoxActivity  activity);

    void  inject(PickingActivity activity);

    void  inject(RecheckMainActivity  activity);

    void  inject(CheckBusinessActilvity  actilvity);

    void  inject(BillModifyActivity  activity);


}
