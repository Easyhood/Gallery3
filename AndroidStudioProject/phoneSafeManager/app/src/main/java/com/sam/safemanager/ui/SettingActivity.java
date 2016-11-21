/**
 * 
 */
package com.sam.safemanager.ui;

import com.sam.safemanager.R;
import com.umeng.socialize.controller.UMServiceFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author Sam
 * @date 2013-5-15
 * @weibo:��ũ����ɣ
 */
public class SettingActivity extends Activity implements OnClickListener{
	TextView tvShare,tvAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.act_settings);
		
		tvShare = (TextView) findViewById(R.id.tv_share);
		tvShare.setOnClickListener(this);
		tvAbout = (TextView) findViewById(R.id.tv_about);
		tvAbout.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_share:
			UMServiceFactory.shareTo(SettingActivity.this, "������ʹ�ã������������ֻ��ܼң���Ҳ���ðɣ�",null);
			break;
		case R.id.tv_about:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("����").setMessage("��������ɣ������д��ϵ���˱�ҵ��ƣ�������ʦָ������������Ҫʵ���ֻ�������ͨѶ��ʿ��������ء�ϵͳ�Ż��ȹ��ܣ����뿪Դ������github�ϡ�");
			builder.create().show();
			break;
		}
		
	}

}
