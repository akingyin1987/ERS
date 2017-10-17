package com.askfood.ers.base;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.base.dialog.ToastUtil;
import com.askfood.ers.base.utils.NetworkUtil;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Anthony on 2016/4/24.
 * Class Note:
 * 1 所有的activity继承于这个类，
 * 2 实现BaseView中的方法（处理进度条，显示对话框）
 */
public abstract class AbsBaseActivity<T extends BasePresenter> extends AppCompatActivity
    implements BaseView {
  protected  String TAG_LOG = null;// Log tag

  @Inject
   protected T mPresenter;
  protected Context mContext = null;//context



  public ProgressDialog pd;

  public Consumer<Disposable> doOnConsmer;




  private boolean showLoading = true;


  @Override protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    mContext = this;

    TAG_LOG = this.getClass().getSimpleName();

    AppManager.getAppManager().addActivity(this);


    if (getContentViewID() != 0) {
      setContentView(getContentViewID());
    }

    init();
    //mUnBinder = ButterKnife.bind(this);
    injectDagger();

    initToolBar();

    if(null != mPresenter){

      mPresenter.attachView(this);
      mPresenter.initialization();
    }
    initEventAndData();
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

  private CompositeDisposable mCompositeSubscription;

  //用于添加rx的监听的在onDestroy中记得关闭不然会内存泄漏。

  public void bindSubscription(Disposable subscription) {
    if (this.mCompositeSubscription == null) {
      this.mCompositeSubscription = new CompositeDisposable();
    }
    this.mCompositeSubscription.add(subscription);
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mPresenter != null) {
      mPresenter.detachView();
    }

    if (mCompositeSubscription != null && !mCompositeSubscription.isDisposed()) {
        mCompositeSubscription.dispose();
    }
    AppManager.getAppManager().finishActivity(this);
  }


  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

  }



  /**
   * init views and events here
   */
  protected abstract void initEventAndData();

  /**
   * bind layout resource file
   */
  protected abstract int getContentViewID();

  /**
   * Dagger2 use in your application module(not used in 'base' module)
   */
  protected abstract void injectDagger();

  /**
   * todo init tool bar
   */
  protected abstract void initToolBar();



  @Override public void close() {
    finish();
  }

  @Override
  public void showMessage(String msg) {

    ToastUtil.showInfo(this,msg);
  }

  @Override
  public void showTips(String msg) {
     ToastUtil.showNormal(this,msg);
  }

  protected Dialog   loadDialog;
  @Override
  public void showLoadDialog(String msg) {
    hideLoadDialog();
    loadDialog = DialogUtil.showLoadDialog(this,msg,null);
  }

  @Override
  public void hideLoadDialog() {
   DialogUtil.hideLoadDialog(loadDialog);
  }

  @Override public void showSucces(String msg) {
    ToastUtil.showSucces(this,msg);
  }

  @Override public void showError(String msg) {
    ToastUtil.showError(this,msg);
  }

  @Override public void showWarning(String msg) {

    ToastUtil.showWarning(this,msg);

  }

  public    void    onCancelDialog(){

  }

  private void init() {
    pd = new ProgressDialog(this);
    pd.setMessage("请稍候...");
    pd.setCanceledOnTouchOutside(false);
    pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
      @Override public void onCancel(DialogInterface dialog) {
        onCancelDialog();
      }
    });
    pd.setOnDismissListener(new DialogInterface.OnDismissListener() {
      @Override public void onDismiss(DialogInterface dialog) {
        onCancelDialog();
      }
    });
    doOnConsmer = new Consumer<Disposable>() {
      @Override public void accept(Disposable disposable) throws Exception {
        if (NetworkUtil.isNetworkAvailable(AbsBaseActivity.this)) {
          Timber.d("网络正常"+showLoading);
          if (showLoading) {
            if(pd != null && !pd.isShowing()){
              pd.show();

            }
          }
        } else {
          disposable.dispose();
          showError( "当前网络不可用，请打开网络");
        }
      }
    };

  }


  /**
   * startActivity
   *
   * @param clazz
   */
  protected void readyGo(Class<?> clazz) {
    Intent intent = new Intent(this, clazz);
    startActivity(intent);
  }

  protected void readyGo(String  clazzName) {
    Intent intent = new Intent();
    intent.setClassName(this,clazzName);
    startActivity(intent);
  }

  /**
   * startActivity with bundle
   *
   * @param clazz
   * @param bundle
   */
  protected void readyGo(Class<?> clazz, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (null != bundle) {
      intent.putExtras(bundle);
    }
    startActivity(intent);
  }

  /**
   * startActivity then finish
   *
   * @param clazz
   */
  protected void readyGoThenKill(Class<?> clazz) {
    Intent intent = new Intent(this, clazz);
    startActivity(intent);
    finish();
  }

  protected void readyGoThenKill(String className) {
    Intent intent = new Intent();
    intent.setClassName(this,className);
    startActivity(intent);
    finish();
  }

  /**
   * startActivity with bundle then finish
   *
   * @param clazz
   * @param bundle
   */
  protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (null != bundle) {
      intent.putExtras(bundle);
    }
    startActivity(intent);
    finish();
  }

  /**
   * startActivityForResult
   *
   * @param clazz
   * @param requestCode
   */
  protected void readyGoForResult(Class<?> clazz, int requestCode) {
    Intent intent = new Intent(this, clazz);
    startActivityForResult(intent, requestCode);
  }

  /**
   * startActivityForResult with bundle
   *
   * @param clazz
   * @param requestCode
   * @param bundle
   */
  protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (null != bundle) {
      intent.putExtras(bundle);
    }
    startActivityForResult(intent, requestCode);
  }



}

