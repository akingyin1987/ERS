package com.askfood.ers.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.askfood.ers.R;
import com.askfood.ers.utils.StringUtils;

/**
 * Created by Administrator on 2017/8/29.
 */

public class DialogUtil {

     public   static    boolean   checkNULL(Context   context){
         if(null == context){
             return  false;
         }
         if(context  instanceof Activity){
             if(((Activity) context).isFinishing()){
                 return  false;
             }
         }
         return  true;
     }

    /**
     * 确认圣诞框
     * @param context
     * @param message
     * @param callBack
     */
     public   static   void    showConfigDialog(Context  context,String   message,final DialogCallBack<Boolean>   callBack){
         new MaterialDialog.Builder(context)
                 .title("提示")
                 .content(message)
                 .positiveText("确定")
                 .negativeText("取消")
                 .onPositive(new MaterialDialog.SingleButtonCallback() {
                     @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                         if(null != callBack){
                             callBack.call(true);
                         }
                     }
                 })
                 .onNegative(new MaterialDialog.SingleButtonCallback() {
                     @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                         if(null != callBack){
                             callBack.call(false);
                         }
                     }
                 })
                 .show();
     }

    /**
     * 确认对话框
     * @param context
     * @param message
     * @param leftName
     * @param rihthName
     * @param cancelable
     * @param callBack
     */

     public   static   void    showConfigDialog(@NonNull Context  context, String  message, String  leftName, String  rihthName, boolean  cancelable, final DialogCallBack<Boolean>  callBack){
         new MaterialDialog.Builder(context)
                  .cancelable(cancelable)
                 .content(message)
                 .positiveText(rihthName)
                 .negativeText(leftName)
                 .onPositive(new MaterialDialog.SingleButtonCallback() {
                     @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                         if(null != callBack){
                             callBack.call(true);
                         }
                     }
                 })
                 .onNegative(new MaterialDialog.SingleButtonCallback() {
                     @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                         if(null != callBack){
                             callBack.call(false);
                         }
                     }
                 })
                 .show();
     }

    /**
     * html显示
     * @param context
     * @param message
     * @param callBack
     */

    public   static   void    showConfigDialog(Context  context, Spanned message, final DialogCallBack<Boolean>   callBack){
        new MaterialDialog.Builder(context)
                .title("提示")
                .content(message)
                .positiveText("确定")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(null != callBack){
                            callBack.call(true);
                        }
                    }
                })
                .negativeText("取消")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(null != callBack){
                            callBack.call(false);
                        }
                    }
                })
                .show();
    }

    /**
     * html显示对话框
     * @param context
     * @param message
     * @param leftName
     * @param rihthName
     * @param cancelable
     * @param callBack
     */
    public   static   void    showConfigDialog(@NonNull Context  context, Spanned  message, String  leftName, String  rihthName, boolean  cancelable, final DialogCallBack<Boolean>  callBack){
        new MaterialDialog.Builder(context)
                .cancelable(cancelable)
                .content(message)
                .positiveText(rihthName)
                .negativeText(leftName)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(null != callBack){
                            callBack.call(true);
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if(null != callBack){
                            callBack.call(false);
                        }
                    }
                })
                .show();
    }





    /**
     * 等待圣诞框
     * @param context
     * @param message
     * @param callBack
     * @return
     */
     public   static    MaterialDialog    showLoadDialog(@NonNull Context  context, String   message, final DialogCallBack<Boolean>   callBack){

       MaterialDialog  materialDialog = new MaterialDialog.Builder(context)

                 .content(TextUtils.isEmpty(message)?"请稍候...":message)
                 .progress(true, 0)
                 .dismissListener(new DialogInterface.OnDismissListener() {
                     @Override
                     public void onDismiss(DialogInterface dialog) {
                         if(null != callBack){
                             callBack.call(false);
                         }
                     }
                 })
                 .cancelListener(new DialogInterface.OnCancelListener() {
                     @Override
                     public void onCancel(DialogInterface dialog) {
                         if(null != callBack){
                             callBack.call(true);
                         }
                     }
                 })
                 .show();
         return  materialDialog;
     }

     public   static  void   hideLoadDialog(Dialog  materialDialog){
         try {
             if(null != materialDialog && materialDialog.isShowing()){
                 materialDialog.dismiss();
             }
         }catch (Exception e){
             e.printStackTrace();
         }

     }


     public   static   void   configPasswork(@NonNull Context  context, final DialogCallBack<String>  dialogCallBack){
         new MaterialDialog.Builder(context)
                 .title("提示")
                 .content("抢单成功，严禁退出")
                 .inputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                 .inputRange(2,12)
                 .input("请输入管理员密码","", new MaterialDialog.InputCallback() {
                     @Override
                     public void onInput(MaterialDialog dialog, CharSequence input) {
                         dialog.dismiss();
                         if(null != dialogCallBack){
                             dialogCallBack.call(input.toString());
                         }
                     }
                 }).show();
     }



     public   static   void   modifySpareDialog(Context  context,final DialogCallBack<Integer>  dialogCallBack){
         new MaterialDialog.Builder(context)
                 .title("零件修改")

                 .inputType(InputType.TYPE_NUMBER_FLAG_SIGNED)
                 .inputRange(1,12)
                 .input("输入实际零货件数","", new MaterialDialog.InputCallback() {
                     @Override
                     public void onInput(MaterialDialog dialog, CharSequence input) {
                         dialog.dismiss();
                         if(null != dialogCallBack){
                             try {
                                 dialogCallBack.call(Integer.parseInt(input.toString()));
                             }catch (Exception e){
                                 e.printStackTrace();
                             }

                         }
                     }
                 }).show();
     }


     public   static   void   modifyAraeFoodDialog(Context  context,final  DialogCallBack<String> dialogCallBack){
         new MaterialDialog.Builder(context)
                 .title("区货修改")

                 .inputType(InputType.TYPE_CLASS_TEXT)
                 .inputRange(1,22)
                 .input("输入单据编号后几位","", new MaterialDialog.InputCallback() {
                     @Override
                     public void onInput(MaterialDialog dialog, CharSequence input) {
                         dialog.dismiss();
                         if(null != dialogCallBack){
                             try {
                                 dialogCallBack.call(input.toString());
                             }catch (Exception e){
                                 e.printStackTrace();
                             }

                         }
                     }
                 }).show();
     }


     public   static   void  modifyAraeFoodConfigDialog(Context  context,String billnumber,String clientname,String oldarea,final DialogCallBack<String> dialogCallBack){
      MaterialDialog  materialDialog =   new MaterialDialog.Builder(context)
                 .title("区货修改")
                 .customView(R.layout.dialog_modiey_bill,true)
                 .positiveText("确定")
                 .onPositive(new MaterialDialog.SingleButtonCallback() {
                     @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                         if(null != dialogCallBack){
                                View    view = dialog.getCustomView();
                               EditText  editText = (EditText) view.findViewById(R.id.tv_new_area);
                                dialogCallBack.call(editText.getText().toString().trim());
                         }
                     }
                 })
                 .negativeText("取消")
                 .onNegative(new MaterialDialog.SingleButtonCallback() {
                     @Override
                     public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                         if(null != dialogCallBack){
                             dialogCallBack.call(null);
                         }
                     }
                 })
                 .show();
         View view = materialDialog.getCustomView();

         TextView   tv_bill_number = (TextView)view.findViewById(R.id.tv_bill_number);
         TextView   tv_client_name =(TextView)view.findViewById(R.id.tv_client_name);
         TextView   tv_ole_area =(TextView)view.findViewById(R.id.tv_ole_area);
         tv_bill_number.setText(StringUtils.nullStrToEmpty(billnumber));
         tv_ole_area.setText(StringUtils.nullStrToEmpty(oldarea));
         tv_client_name.setText(StringUtils.nullStrToEmpty(clientname));
     }

}
