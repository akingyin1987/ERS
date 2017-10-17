package com.askfood.ers.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.askfood.ers.R;
import com.askfood.ers.base.widget.BrowserLayout;


/**
 * @ Description:
 *
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date 2016/7/12 17:25
 * @ Version V1.0
 */
public class BaseWebFragment  extends  SimpleFragment {


  public static final String BUNDLE_KEY_URL = "BUNDLE_KEY_URL";
  public static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
  public static final String BUNDLE_KEY_SHOW_BOTTOM_BAR = "BUNDLE_KEY_SHOW_BOTTOM_BAR";

  private String mWebUrl = null;
  private String mWebTitle = null;
  private boolean isShowBottomBar = true;

  private Toolbar mToolBar = null;
  private BrowserLayout mBrowserLayout = null;


  public static   BaseWebFragment   newWebFragment(String  url,boolean  isShowBottomBar){

    Bundle   bundle  =  new Bundle();
    bundle.putBoolean(BUNDLE_KEY_SHOW_BOTTOM_BAR,isShowBottomBar);
    bundle.putString(BUNDLE_KEY_URL,url);
    BaseWebFragment  fragment = new BaseWebFragment();
    fragment.setArguments(bundle);
    return fragment;
  }





  @Override protected int getContentViewID() {
    return R.layout.activity_common_web;
  }




  @Override protected void initDagger() {
   // activityComponent().inject(this);
  }

  @Override protected void initEventAndData(View rootView) {
    mToolBar =(Toolbar)rootView .findViewById(R.id.common_toolbar);
    mToolBar.setVisibility(View.GONE);
    mBrowserLayout =(BrowserLayout)rootView.findViewById( R.id.common_web_browser_layout);
    Bundle  bundle = getArguments();
    mWebTitle = bundle.getString(BUNDLE_KEY_TITLE);
    mWebUrl =bundle.getString(BUNDLE_KEY_URL);
    isShowBottomBar = bundle.getBoolean(BUNDLE_KEY_SHOW_BOTTOM_BAR,false);


    if (!TextUtils.isEmpty(mWebUrl)) {
      mBrowserLayout.loadUrl(mWebUrl);
    } else {
     // toastUtils.showToast("获取URL地址失败");
    }

    if (!isShowBottomBar) {
      mBrowserLayout.hideBrowserController();
    } else {
      mBrowserLayout.showBrowserController();
    }
  }

  @Override
  protected void initButterKnife(View view) {

  }
}
