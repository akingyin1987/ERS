package com.askfood.ers.encryption;

import android.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC是密钥相关的哈希运算消息认证码，
 * HMAC运算利用哈希算法，以一个密钥和一个消息为输入，生成一个消息摘要作为输出
 * @ Description:
 * @ Author king
 * @ Date 2017/8/1 16:11
 * @ Version V1.0
 */

public class HMac {
  public static byte[] decryptBASE64(String key) throws Exception {
    return Base64.decode(key, Base64.DEFAULT);
  }
  public static String encryptBASE64(byte[] key) throws Exception {
    return Base64.encodeToString(key, Base64.DEFAULT);
  }

  /**
   * 初始化HMAC密钥
   *
   * @return
   * @throws Exception
   */
  public static String initMacKey() throws Exception {
    KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");

    SecretKey secretKey = keyGenerator.generateKey();
    return encryptBASE64(secretKey.getEncoded());
  }

  /**
   * HMAC加密
   *
   * @param data
   * @param key
   * @return
   * @throws Exception
   */
  public static byte[] encryptHMAC(byte[] data, String key, String method) throws Exception {

    //SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), "HmacMD5");
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), method);//直接用给定的字符串进行
    Mac mac = Mac.getInstance(secretKey.getAlgorithm());
    mac.init(secretKey);

    return mac.doFinal(data);

  }
}
