package com.askfood.ers.base;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @ Description:
 * @ Author king
 * @ Date 2017/3/4 16:02
 * @ Version V1.0
 */

public abstract class SimpleFragment extends Fragment{

  /**
   * Log tag
   */
  protected static String TAG_LOG = null;
  /**
   * url passed into fragment
   */
  public static String EXTRA_URL = "url";
  private String mUrl;
  /**
   * activity context of fragment
   */
  protected Context mContext;
  protected View mView;
  protected Activity mActivity;


  protected boolean isInited = false;






  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    TAG_LOG = this.getClass().getSimpleName();

    if (getArguments() != null) {
      mUrl = getArguments().getString(EXTRA_URL);
    }
    initDagger();
  }

  protected abstract void initDagger();

  @Override
  public void onAttach(Context context) {
    this.mContext = context;
    if(context  instanceof  Activity){
      mActivity = (Activity) context;
    }
    super.onAttach(context);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    if (getContentViewID() != 0) {
      return inflater.inflate(getContentViewID(), null);
    } else {
      return super.onCreateView(inflater, container, savedInstanceState);
    }

  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initButterKnife(view);

    initEventAndData(view);
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();


  }




  public String getFragmentUrl() {
    return mUrl;
  }

  protected abstract   void   initButterKnife(View view);


  /**
   * override this method to do operation in the fragment
   */
  protected abstract void initEventAndData(View rootView);


  /**
   * override this method to return content view id of the fragment
   */
  protected abstract int getContentViewID();


}
