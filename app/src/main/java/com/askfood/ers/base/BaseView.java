package com.askfood.ers.base;

/**
 * @ Description:
 *  MVP V层接口
 * Company:重庆中陆承大科技有限公司
 * @ Author king
 * @ Date ${DATE} ${TIME}
 * @ Version V1.0
 */
public interface BaseView {

    void showMessage(String msg);

    void showSucces(String msg);

    void showError(String msg);

    void showWarning(String msg);

    void close();

    void showTips(String msg);

    void showLoadDialog(String msg);

    void hideLoadDialog();
}
