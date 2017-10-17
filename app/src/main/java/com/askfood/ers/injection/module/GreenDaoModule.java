/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.injection.module;

import android.content.Context;

import com.askfood.ers.daocore.db.dao.DaoSession;
import com.askfood.ers.daocore.db.dao.UserDao;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/31.
 */

@Module
public class GreenDaoModule {
    protected final DaoSession mDaoSession;

    protected Context   mContext;

    public GreenDaoModule(DaoSession daoSession, Context context) {
        mDaoSession = daoSession;
        mContext = context;
    }


    @Provides
    UserDao    getUserDao(){
        return  mDaoSession.getUserDao();
    }





}
