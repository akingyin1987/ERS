package com.askfood.ers.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.askfood.ers.base.dialog.DialogUtil;
import com.askfood.ers.base.dialog.ToastUtil;


//import butterknife.ButterKnife;

/**
 * Created by Anthony on 2016/2/25.
 * Class Note:
 *
 * Base Fragment for all the Fragment defined in the project
 * 1 extended from {@link AbsBaseFragment} to do
 * some base operation.
 * 2 do operation in initViewAndEvents(){}
 */
public abstract class AbsBaseFragment<T extends BasePresenter> extends Fragment implements BaseView{
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






    protected T mPresenter;


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
        mPresenter = getmPresenter();
        if(null != mPresenter){

            mPresenter.attachView(this);
        }

        initEventAndData(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();


        if(null != mPresenter){
            mPresenter.detachView();
        }
    }


    public String getFragmentUrl() {
        return mUrl;
    }


    protected   abstract   T   getmPresenter();


    /**
     * override this method to do operation in the fragment
     */
    protected abstract void initEventAndData(View rootView);


    /**
     * override this method to return content view id of the fragment
     */
    protected abstract int getContentViewID();



    /**
     * implements methods in BaseView
     */

    @Override public void close() {

    }

    @Override
    public void showMessage(String msg) {

        ToastUtil.showInfo(getContext(),msg);
    }

    @Override
    public void showTips(String msg) {
        ToastUtil.showNormal(getContext(),msg);
    }


    protected Dialog    loadDialog;
    @Override
    public void showLoadDialog(String msg) {
        hideLoadDialog();
       loadDialog = DialogUtil.showLoadDialog(getContext(),msg,null);
    }

    @Override
    public void hideLoadDialog() {
        DialogUtil.hideLoadDialog(loadDialog);
    }

    @Override public void showSucces(String msg) {
        ToastUtil.showSucces(getContext(),msg);
    }

    @Override public void showError(String msg) {
        ToastUtil.showError(getContext(),msg);
    }

    @Override public void showWarning(String msg) {

        ToastUtil.showWarning(getContext(),msg);

    }
}

