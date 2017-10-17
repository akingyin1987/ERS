package com.askfood.ers.base;

/**
 * @ Description:
 * MVP中所有Presenter的接口，完成view的绑定和解除
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @
 * @ Version V1.0
 */
public interface BasePresenter<T extends BaseView> {
    /**
     * 注入View，使之能够与View相互响应
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 释放资源
     */
    void detachView();

    //初使化
    void  initialization();
}

