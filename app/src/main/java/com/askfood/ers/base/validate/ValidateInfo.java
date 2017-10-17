/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.base.validate;

import java.io.Serializable;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/9/29 13:38
 * @ Version V1.0
 */

public class ValidateInfo  implements Serializable {

  private static final long serialVersionUID = 7604772813494820490L;


  private    boolean    val;

  private    String      msg;

  private    Throwable    mThrowable;

  public ValidateInfo(boolean val, String msg, Throwable throwable) {
    this.val = val;
    this.msg = msg;
    mThrowable = throwable;
  }

  public ValidateInfo(boolean val, String msg) {
    this.val = val;
    this.msg = msg;
  }

  public ValidateInfo() {
  }

  public boolean isVal() {
    return val;
  }

  public void setVal(boolean val) {
    this.val = val;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Throwable getThrowable() {
    return mThrowable;
  }

  public void setThrowable(Throwable throwable) {
    mThrowable = throwable;
  }
}
