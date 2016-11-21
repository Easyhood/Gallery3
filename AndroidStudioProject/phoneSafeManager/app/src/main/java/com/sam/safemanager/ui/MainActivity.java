/**
 * 
 */
package com.sam.safemanager.ui;

import com.sam.safemanager.R;
import com.sam.safemanager.adapter.MainUIAdapter;
import com.sam.safemanager.util.Logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * @author Sam
 * @date 2013-4-21
 * @weibo:��ũ����ɣ
 */
public class MainActivity extends Activity implements OnItemClickListener{
	private GridView gv_main;
	private MainUIAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.act_main);
		
		gv_main = (GridView) findViewById(R.id.gv_main);
		adapter = new MainUIAdapter(this);
		gv_main.setAdapter(adapter);
		gv_main.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch(position){
		case 0:   //�ֻ�����
			Intent toLost = new Intent(MainActivity.this,LostProtectedActivity.class);
			startActivity(toLost);
			break;
		case 1:
			Logger.i("ͨѶ��ʿ");
			Intent callsmsIntent = new Intent(MainActivity.this,
					CallSmsActivity.class);
			startActivity(callsmsIntent);
			break;
		case 2:
			Logger.i("�������");
			Intent appmanagerIntent = new Intent(MainActivity.this,
					AppManagerActivity.class);
			startActivity(appmanagerIntent);
			break;
		case 3:
			Logger.i("�������");
			Intent taskmanagerIntent = new Intent(MainActivity.this,
					TaskManagerActivity.class);
			startActivity(taskmanagerIntent);
			break;
		case 4:
			Logger.i("��������");
			Intent trafficmanagerIntent = new Intent(MainActivity.this,
					TrafficManagerActivity.class);
			startActivity(trafficmanagerIntent);
			break;
		case 5:
			Logger.i("ϵͳ�Ż�");
			Intent cleanIntent = new Intent(MainActivity.this,CleanActivity.class);
			startActivity(cleanIntent);
			break;
		case 6:
			Logger.i("�߼�����");
			Intent atoolsIntent = new Intent(MainActivity.this,AToolsActivity.class);
			startActivity(atoolsIntent);
			break;
		case 7:
			Logger.i("��������");
			Intent settingsIntent = new Intent(MainActivity.this,SettingActivity.class);
			startActivity(settingsIntent);
			break;
		
		}
		
	}
	

}
