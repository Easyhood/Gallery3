/**
 * 
 */
package com.sam.safemanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Sam
 * @date 2013-5-6
 * @weibo:��ũ����ɣ
 */
public class BlackNumberDBHelper extends SQLiteOpenHelper{
	public BlackNumberDBHelper(Context context) {
		super(context, "blacknumber.db", null, 1);
	}

	/**
	 * ��һ�δ������ݿ��ʱ��ִ�� oncreate����
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE blacknumber (_id integer primary key autoincrement, number varchar(20))");
	}

	/**
	 * �������ݿ�Ĳ���
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
