package com.askfood.ers.singn;


import com.askfood.ers.encryption.MD5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/8/31 17:35
 * @ Version V1.0
 */

public final class Singn {

  /**
   * 创建签名
   * @param params
   * @param encode
   * @return
   * @throws UnsupportedEncodingException
   */
  public static String createSign(Map<String, String> params, boolean encode)
      {
    Set<String> keysSet = params.keySet();
    Object[] keys = keysSet.toArray();
    Arrays.sort(keys);
    StringBuffer temp = new StringBuffer();
    boolean first = true;
    for (Object key : keys) {
      if (first) {
        first = false;
      } else {
        temp.append("&");
      }
      temp.append(key).append("=");
      Object value = params.get(key);
      String valueString = "";
      if (null != value) {
        valueString = String.valueOf(value);
      }
      if (encode) {
        try {
          temp.append(URLEncoder.encode(valueString, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      } else {
        temp.append(valueString);
      }
    }

    return MD5.getMD5(temp.toString()).toUpperCase();
  }
}
