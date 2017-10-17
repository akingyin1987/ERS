/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.base.widget.banner;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/6.
 */

public interface ImageLoaderInterface <T extends View> extends Serializable{

    void displayImage(Context context, Object path, T imageView);

    T createImageView(Context context);
}
