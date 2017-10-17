/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.presenter;


import com.askfood.ers.base.BasePresenter;
import com.askfood.ers.base.BaseView;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/9/4 16:38
 * @ Version V1.0
 */

public interface LoginContract {

  interface View extends BaseView {




    void    goToHomeActivity();

    void   cleanUserName();

    void   cleanPass();

    void   showPass();

    void   login();

  }

  interface Presenter extends BasePresenter<View> {

     void    checkLingInfo(String userName, String passWord);



    //取消当前定阅
    void    cancelSubscribe();
  }
}
