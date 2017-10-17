package com.askfood.ers.encryption;


import com.askfood.ers.utils.ConvertUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/8/1 17:43
 * @ Version V1.0
 */

public class MD5 {

  public static String getMD5(String message) {
    String md5str = "";
    try {
      // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
      MessageDigest md = MessageDigest.getInstance("MD5");

      // 2 将消息变成byte数组
      byte[] input = message.getBytes();

      // 3 计算后获得字节数组,这就是那128位了
      byte[] buff = md.digest(input);

      // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
      md5str = ConvertUtils.bytesToHexString(buff);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return md5str;
  }

  public static byte[] encryptMD5(byte[] data) throws Exception {

    MessageDigest md5 = MessageDigest.getInstance("MD5");
    md5.update(data);
    return md5.digest();

  }

  /**
   * 获取文件MD5值
   * @param file
   * @return
   * @throws FileNotFoundException
   */
  public static String getMd5ByFile(File file) throws FileNotFoundException {
    String value = null;
    FileInputStream in = new FileInputStream(file);
    try {
      MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(byteBuffer);
      value = ConvertUtils.bytesToHexString(md5.digest());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(null != in) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return value;
  }
}
