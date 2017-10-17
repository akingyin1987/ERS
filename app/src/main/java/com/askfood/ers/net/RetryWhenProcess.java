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

package com.askfood.ers.net;

/**
 * @ Description:
 * 网络请求时自动重试//使用时：.retryWhen(new RetryWhenProcess(5))
 *   rxjava1机制
 * @ Author king
 * @ Date 2016/4/14 14:53
 * @ Version V1.0
 */
//public class RetryWhenProcess  implements Func1<Observable<? extends Throwable>, Observable<?>>{
//  private long mInterval;
//
//  public RetryWhenProcess(long mInterval) {
//    this.mInterval = mInterval;
//  }
//
//  @Override
//  public Observable<?> call(Observable<? extends Throwable> observable) {
//    return observable.flatMap(new Func1<Throwable, Observable<?>>() {
//      @Override
//      public Observable<?> call(Throwable throwable) {
//        //未找开网络直接退出程序
//        if (throwable instanceof UnknownHostException) {
//          return Observable.error(throwable);
//        }
//        //最多重试5次
//        return Observable.just(throwable).zipWith(Observable.range(1, 5), new Func2<Throwable, Integer, Integer>() {
//          @Override
//          public Integer call(Throwable throwable, Integer integer) {
//            return integer;
//          }
//        }).flatMap(new Func1<Integer, Observable<? extends  Long>>() {
//          @Override public Observable<? extends  Long> call(Integer retryCount) {
//            return Observable.timer((long) Math.pow(mInterval, retryCount), TimeUnit.SECONDS);
//          }
//        });
//      }
//    });
//  }
//}
