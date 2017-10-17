package com.askfood.ers.presenter;

import com.askfood.ers.base.BasePresenter;
import com.askfood.ers.base.BaseView;

/**
 * Created by Administrator on 2017/10/15.
 */

public interface HomeContract {
    interface View extends BaseView {




        void    goToEncasementActivity();

        void    goToReviewActivity();

    }

    interface Presenter extends BasePresenter<HomeContract.View> {


    }

}
