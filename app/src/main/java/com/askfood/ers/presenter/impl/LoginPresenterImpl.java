/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.presenter.impl;


import com.askfood.ers.presenter.LoginContract;

import javax.inject.Inject;


/**
 * @ Description:
 * @ Author king
 * @ Date 2017/9/4 17:57
 * @ Version V1.0
 */

public class LoginPresenterImpl  implements LoginContract.Presenter {



  @Inject
  public LoginPresenterImpl() {
  }

  LoginContract.View   mView;


  @Override public void attachView(LoginContract.View view) {

    this.mView = view;
  }

  @Override public void detachView() {

  }

  @Override public void initialization() {


  }



  @Override public void checkLingInfo(final String userName, String passWord) {



  }






  @Override public void cancelSubscribe() {

  }
}
