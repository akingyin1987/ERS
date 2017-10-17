package com.askfood.ers.base;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


/**
 * @ Description:
 * @ Author king
 * @ Date 2017/3/4 15:53
 * @ Version V1.0
 */

public  abstract class SimpleActivity extends AppCompatActivity {

  protected Activity mContext;
 // private Unbinder mUnBinder;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(getLayout()!=0){
      setContentView(getLayout());
    }
  //  mUnBinder = ButterKnife.bind(this);
    mContext = this;
    initInject();

    AppManager.getAppManager().addActivity(this);
    initEventAndData();
  }




  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  protected void setToolBar(Toolbar toolbar, String title) {
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
    if(null != getSupportActionBar()){
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          onBackPressed();
        }
      });
    }

  }
  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

  }
  protected abstract void initInject();
  protected abstract int getLayout();
  protected abstract void initEventAndData();
}
