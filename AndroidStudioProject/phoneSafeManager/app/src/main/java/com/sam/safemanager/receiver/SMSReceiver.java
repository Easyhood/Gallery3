package com.sam.safemanager.receiver;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sam.safemanager.R;
import com.sam.safemanager.db.dao.BlackNumberDao;
import com.sam.safemanager.util.Logger;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver implements BDLocationListener{
	private SharedPreferences sp;
	public LocationClient mLocationClient = null;
	private MediaPlayer player;
	private Context context;
	String sender;
	private BlackNumberDao dao;
	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		dao = new BlackNumberDao(context);
		//��ȡ��������
		//#*location*#����
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		for(Object pdu:pdus){
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
			String content = sms.getMessageBody();
			Logger.i("��������:"+content);
			sender = sms.getOriginatingAddress();
			if("#*location*#".equals(content)){
				abortBroadcast();
				mLocationClient = new LocationClient(context.getApplicationContext());     //����LocationClient��
				mLocationClient.registerLocationListener( this ); //ע���������
				LocationClientOption option = new LocationClientOption();
				option.setOpenGps(true);
				option.setAddrType("all");//���صĶ�λ���������ַ��Ϣ
				option.setCoorType("bd09ll");//���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
				option.setScanSpan(5000);//���÷���λ����ļ��ʱ��Ϊ5000ms
				option.disableCache(true);//��ֹ���û��涨λ	
				mLocationClient.setLocOption(option);
				mLocationClient.start();
				if (mLocationClient != null && mLocationClient.isStarted())
					mLocationClient.requestLocation();
				else 
					Logger.d("locClient is null or not started");
			}else if("#*alram*#".equals(content)){
				abortBroadcast();
				player = MediaPlayer.create(context, R.raw.alert);
				player.setVolume(1.0f, 1.0f);
				player.setLooping(true);
				player.start();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(18000);
							player.stop();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}).start();
			}else if("#*lockscreen*#".equals(content)){
				DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
				manager.resetPassword("123", 0);
				manager.lockNow();
				abortBroadcast();
			}else if("#*wipedata*#".equals(content)){
				DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
				manager.wipeData(0);
				abortBroadcast();
			}
			
			if(dao.find(sender)){
				// �������Ķ���
				abortBroadcast();
				//todo: �Ѷ������ݴ�ŵ��Լ������ݿ�����
			}
			
			//�����������ݵ�ƥ��� (�ؼ���: ��Ʊ,����,��,ѧ��....��֤...)
			if(content.contains("fapiao")){
				Logger.i("�������� ��Ʊ");
				abortBroadcast();
			}
		}
		
	}
	@Override
	public void onReceiveLocation(BDLocation location) {
		if (location == null)
			return ;
		StringBuffer sb = new StringBuffer(256);
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\nradius : ");
		sb.append(location.getRadius());
		if (location.getLocType() == BDLocation.TypeGpsLocation){
			sb.append("\nspeed : ");
			sb.append(location.getSpeed());
			sb.append("\nsatellite : ");
			sb.append(location.getSatelliteNumber());
		} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
			sb.append("\naddr : ");
			sb.append(location.getAddrStr());
		} 
		SmsManager smsmanager = SmsManager.getDefault();
		if ("".equals(sb.toString()) ) {

		} else {
			smsmanager.sendTextMessage(sender, null, sb.toString(), null,
					null);
		}
		Logger.i(sb.toString());
		Toast.makeText(context, sb.toString(),10).show();
		mLocationClient.stop();
	}
	@Override
	public void onReceivePoi(BDLocation arg0) {
		// TODO Auto-generated method stub
		
	}

}
