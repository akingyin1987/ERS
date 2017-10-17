/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.askfood.ers.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.askfood.ers.R;
import com.askfood.ers.base.widget.BrowserLayout;


/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    15/7/24
 * Description:
 */
public class BaseWebActivity extends SimpleActivity {

    public static final String BUNDLE_KEY_URL = "BUNDLE_KEY_URL";
    public static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    public static final String BUNDLE_KEY_SHOW_BOTTOM_BAR = "BUNDLE_KEY_SHOW_BOTTOM_BAR";

    private String mWebUrl = null;
    private String mWebTitle = null;
    private boolean isShowBottomBar = true;

    private Toolbar mToolBar = null;
    private BrowserLayout mBrowserLayout = null;



    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebTitle = getIntent().getStringExtra(BUNDLE_KEY_TITLE);
        mWebUrl =getIntent().getStringExtra(BUNDLE_KEY_URL);
        isShowBottomBar = getIntent().getBooleanExtra(BUNDLE_KEY_SHOW_BOTTOM_BAR,false);
    }

    @Override
    protected void initEventAndData() {

        mToolBar =(Toolbar) findViewById(R.id.common_toolbar);
        mBrowserLayout =(BrowserLayout)findViewById( R.id.common_web_browser_layout);

        if (null != mToolBar) {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (!TextUtils.isEmpty(mWebTitle)) {
            setTitle(mWebTitle);
        } else {
            setTitle("网页");
        }

        if (!TextUtils.isEmpty(mWebUrl)) {
            mBrowserLayout.loadUrl(mWebUrl);
        } else {
            //toastUtils.showToast("获取URL地址失败");
        }

        if (!isShowBottomBar) {
            mBrowserLayout.hideBrowserController();
        } else {
            mBrowserLayout.showBrowserController();
        }
    }

    @Override protected void initInject() {

    }

    @Override protected int getLayout() {
        return R.layout.activity_common_web;
    }






}
