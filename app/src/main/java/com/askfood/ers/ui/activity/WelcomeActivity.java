package com.askfood.ers.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.askfood.ers.R;
import java.util.Calendar;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/8/28 15:14
 * @ Version V1.0
 */

public class WelcomeActivity extends AppCompatActivity {

  @BindView(R.id.splash_image) ImageView mSplashImage;
  @BindView(R.id.splash_app_name) TextView mSplashAppName;
  @BindView(R.id.splash_slogan) TextView mSplashSlogan;
  @BindView(R.id.splash_version_name) TextView mSplashVersionName;
  @BindView(R.id.splash_copyright) TextView mSplashCopyright;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_welcome);
   // StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    ButterKnife.bind(this);

    mSplashCopyright.setText(getCopyright(this));
    mSplashVersionName.setText(getVersionName(this));
    mSplashImage.setImageResource(getBackgroundImageResID());
    Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
       goHomePage();
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    mSplashImage.startAnimation(animation);

  }


  public   void   goHomePage(){
    Intent  intent = new Intent(this,HomeActivity.class);
    startActivity(intent);
    finish();

  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      return false;
    }
    return super.onKeyDown(keyCode, event);
  }
  public String getVersionName(Context context) {
    String versionName = null;
    try {
      versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return String.format(context.getResources().getString(R.string.splash_version), versionName);
  }

  public String getCopyright(Context context) {
    return context.getResources().getString(R.string.splash_copyright);
  }
  public int getBackgroundImageResID() {
    int resId=R.drawable.afternoon;
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(calendar.HOUR_OF_DAY);
    if (hour >= 6 && hour <= 12) {
     // resId = R.drawable.morning;
    } else if (hour > 12 && hour <= 18) {
      resId = R.drawable.afternoon;
    } else {
     // resId = R.drawable.night;
    }
    return resId;
  }

}
