/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.askfood.ers.daocore.db.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @ Description:
 * @ Author king
 * @ Date 2017/9/15 11:50
 * @ Version V1.0
 */

public class DatabaseHelper  extends SQLiteOpenHelper {



  private static final int VERSION = 1;

  private  static  final  String   DB_NAME="com.zlcdgroup.user-db";

  /**
   * 在SQLiteOpenHelper的子类当中，必须有该构造函数
   * @param context   上下文对象
   * @param name      数据库名称
   * @param factory
   * @param version   当前数据库的版本，值必须是整数并且是递增的状态
   */
  public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
      int version) {
    //必须通过super调用父类当中的构造函数
    super(context, name, factory, version);
  }

  public DatabaseHelper(Context context, String name, int version){
    this(context,name,null,version);
  }

  public DatabaseHelper(Context context, String name){
    this(context,name,VERSION);
  }


  public DatabaseHelper(Context  context){
    this(context,DB_NAME);
  }

  //该函数是在第一次创建的时候执行，实际上是第一次得到SQLiteDatabase对象的时候才会调用这个方法
  @Override
  public void onCreate(SQLiteDatabase db) {

    //execSQL用于执行SQL语句
    String constraint =  "IF NOT EXISTS ";
    db.execSQL("CREATE TABLE " + constraint + "\"tb_user\" (" + //
        "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: localId
        "\"USER_ID\" TEXT," + // 1: userId
        "\"NUMBER_ID\" INTEGER," + // 2: numberId
        "\"ACCOUNT\" TEXT," + // 3: account
        "\"NAME\" TEXT," + // 4: name
        "\"TOKEN\" TEXT," + // 5: token
        "\"EXTENDED_TIME\" INTEGER," + // 6: extendedTime
        "\"REFRESH_TOKEN\" TEXT," + // 7: refreshToken
        "\"USER_REMARK\" TEXT," + // 8: userRemark
        "\"USER_NICK_NAME\" TEXT," + // 9: userNickName
        "\"LOGIN_TIME\" INTEGER," + // 10: loginTime
        "\"USER_AVATAR\" TEXT," + // 11: userAvatar
        "\"ELAPSED_REALTIME\" INTEGER);"); // 12: elapsedRealtime
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
