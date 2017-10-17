/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.injection.component;

import android.app.Application;
import android.content.Context;

import com.askfood.ers.ERSApp;
import com.askfood.ers.injection.module.ApplicationModule;
import com.askfood.ers.injection.module.GreenDaoModule;
import com.askfood.ers.injection.scope.ApplicationContext;

import dagger.Component;
import javax.inject.Singleton;

/**
 * @ Description:
 *  单列组件，生命周期承APP 一起
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date ${DATE} ${TIME}
 * @ Version V1.0
 */
@Singleton
@Component(modules = {ApplicationModule.class, GreenDaoModule.class})
public interface ApplicationComponent {

    void inject(ERSApp application);

    @ApplicationContext
    Context context();

    Application application();








}
