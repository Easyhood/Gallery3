package com.sam.safemanager.receiver;

import com.sam.safemanager.util.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {
	private SharedPreferences sp;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Logger.i("�������");
		// �ж��ֻ��Ƿ��ڱ���״̬
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean isprotecting = sp.getBoolean("isprotecting", false);
		if(isprotecting){
			TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String currentsim =  manager.getSimSerialNumber();
			String realsim = sp.getString("sim", "");
			if(!currentsim.equals(realsim)){ //sim �����Ų�ͬ
				// ���ͱ�������
				Logger.i("���ͱ�������");
				SmsManager smsmanager = SmsManager.getDefault();
			    String destinationAddress =	sp.getString("safenumber", "");
				smsmanager.sendTextMessage(destinationAddress, null, "sim�������˸ı�,�ֻ����ܱ���", null, null);
				sp.edit().putBoolean("changesim", true).commit();
			}
		}

	}

}
