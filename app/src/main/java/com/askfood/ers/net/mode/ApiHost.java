/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.net.mode;


import com.askfood.ers.net.config.CommonConstants;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ApiHost {

  private static String host = CommonConstants.API_HOST;

  public static String getHost() {
    return host;
  }

  public static void setHost(String url) {
    host = url;
  }

  public static void setHostHttp(String url) {
    if (url.startsWith("https://") || url.startsWith("http://")) {
      host = url;
      host = host.replaceAll("https://", "http://");
    } else {
      host = "http://" + url;
    }
  }

  public static void setHostHttps(String url) {
    if (url.startsWith("https://") || url.startsWith("http://")) {
      host = url;
      host = host.replaceAll("http://", "https://");
    } else {
      host = "https://" + url;
    }
  }

  public static String getHttp() {
    if (host.startsWith("https://") || host.startsWith("http://")) {
      host = host.replaceAll("https://", "http://");
    } else {
      host = "http://" + host;
    }
    return host;
  }

  public static String getHttps() {
    if (host.startsWith("https://") || host.startsWith("http://")) {
      host = host.replaceAll("http://", "https://");
    } else {
      host = "https://" + host;
    }
    return host;
  }
}
