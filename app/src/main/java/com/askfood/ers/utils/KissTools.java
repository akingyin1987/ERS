/*
 *
 *   Copyright (c) 2016 [akingyin@163.com]
 *
 *   Licensed under the Apache License, Version 2.0 (the "License”);
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.askfood.ers.utils;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.security.InvalidParameterException;

/**
 * 保存context 上下文
 * Created by Administrator on 2016/5/25.
 */

public class KissTools {
    public static final String TAG = "KissTools";
    private static WeakReference<Context> contextRef;

    public static void setContext(Context context) {
        if (context == null) {
            throw new InvalidParameterException("Invalid context parameter!");
        }

        Context appContext = context.getApplicationContext();
        contextRef = new WeakReference<>(appContext);
    }

    public static Context getApplicationContext() {
        Context context = contextRef.get();
        if (context == null) {
            throw new InvalidParameterException("Context parameter not set!");
        } else {
            return context;
        }
    }

}
