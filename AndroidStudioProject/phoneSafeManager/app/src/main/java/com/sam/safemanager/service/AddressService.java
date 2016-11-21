/**
 * 
 */
package com.sam.safemanager.service;



import java.lang.reflect.Method;


import com.android.internal.telephony.ITelephony;
import com.sam.safemanager.R;
import com.sam.safemanager.db.dao.BlackNumberDao;
import com.sam.safemanager.engine.NumberAddressService;
import com.sam.safemanager.ui.CallSmsActivity;
import com.sam.safemanager.util.Logger;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Sam
 * @date 2013-5-6
 * @weibo:��ũ����ɣ
 */
public class AddressService extends Service {
	private TelephonyManager manager;
	private MyPhoneListener listener;
	private View view;
	private WindowManager windowManager;
	private SharedPreferences sp;
	private BlackNumberDao dao;
	private long firstRingTime;
	private long endRingTime;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//ע��ϵͳ�绰�������ļ�����
		listener = new MyPhoneListener();
		sp = getSharedPreferences("config", MODE_PRIVATE);
		dao = new BlackNumberDao(getApplicationContext());
		manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	public void onDestroy() {
		manager.listen(listener, PhoneStateListener.LISTEN_NONE);
		listener = null;
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MyPhoneListener extends PhoneStateListener{

		/**
		 * �绰״̬�����ı���õķ���
		 */
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch(state){
			case TelephonyManager.CALL_STATE_IDLE: //���ھ�ֹ״̬���޵绰
				endRingTime = System.currentTimeMillis();
				long  calltime = endRingTime-firstRingTime;
				Logger.i("calltime ="+calltime);
				if(firstRingTime<endRingTime && calltime<5000 && calltime >0){
					Logger.i("��һ���ĵ绰");
					endRingTime = 0;
					firstRingTime = 0;
					// ������notification ֪ͨ�û�����һ��ɧ�ŵ绰
					showNotification(incomingNumber);
				}
				
				if(view!=null){
					windowManager.removeView(view);
					view = null;
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK: //
				if(view!=null){
					windowManager.removeView(view);
					view = null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING://����״̬
				firstRingTime = System.currentTimeMillis();
				if(dao.find(incomingNumber)){
					//�Ҷϵ绰
					endCall();
					
					//deleteCallLog(incomingNumber);\
					
					//ע��һ�����ݹ۲��� �۲�call_log��uri����Ϣ 
					
					getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, new MyObserver(new Handler(),incomingNumber));
					
				}else{
					String address = NumberAddressService.getAddress(incomingNumber);
					showLocation(address);
				}
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
		
	}
	
	private void showLocation(String address){
		WindowManager.LayoutParams params = new LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				0, 0, WindowManager.LayoutParams.TYPE_TOAST,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, 
				PixelFormat.TRANSLUCENT);
		params.gravity = Gravity.LEFT | Gravity.TOP;
		
        params.x =     sp.getInt("lastx", 0);
        params.y =     sp.getInt("lasty", 0);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        //view = View.inflate(getApplicationContext(), R.layout.show_location, null);
        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.show_location, null);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_location);

        int backgroundid = sp.getInt("background", 0);
        if(backgroundid==0){
        	ll.setBackgroundResource(R.drawable.call_locate_gray);
        }else if(backgroundid==1){
        	ll.setBackgroundResource(R.drawable.call_locate_orange);
        }else {
        	ll.setBackgroundResource(R.drawable.call_locate_green);
        }
        
        TextView tv = (TextView) view.findViewById(R.id.tv_location);
        tv.setTextSize(24);
        tv.setText(address);
        if(view !=null)
        	windowManager.addView(view , params);
		
	}
	/**
	 * ����notification ֪ͨ�û���Ӻ���������
	 * @param incomingNumber
	 */
	public void showNotification(String incomingNumber) {
		//1. ��ȡnotification�Ĺ������
		NotificationManager  manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//2 ��һ��Ҫ����ʾ��notification ���󴴽�����
		int icon =R.drawable.notification;
		CharSequence tickerText = "������һ������";
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, tickerText, when);
		// 3 .����notification��һЩ����
		Context context = getApplicationContext();
		CharSequence contentTitle = "��һ������";
		CharSequence contentText = incomingNumber;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
		Intent notificationIntent = new Intent(this, CallSmsActivity.class);
		// ����һ���ĺ��� ���õ�intent��������
		notificationIntent.putExtra("number", incomingNumber);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		// 4. ͨ��manger��notification ����
		manager.notify(0, notification);
	}

	/**
	 * ���ݵ绰����ɾ�����м�¼
	 * @param incomingNumber Ҫɾ�����м�¼�ĺ���
	 */
	public void deleteCallLog(String incomingNumber) {
		ContentResolver  resolver = getContentResolver();
		Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, "number=?", new String[]{incomingNumber}, null);
		if(cursor.moveToFirst()){//��ѯ���˺��м�¼
			String id =cursor.getString(cursor.getColumnIndex("_id"));
			resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?",new String[]{id});
		}
	}

	public void endCall() {
		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			IBinder binder = (IBinder)method.invoke(null, new Object[]{TELEPHONY_SERVICE});
			ITelephony telephony = ITelephony.Stub.asInterface(binder);
			telephony.endCall();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private class MyObserver extends ContentObserver
	{
		private String incomingnumber;
		public MyObserver(Handler handler,String incomingnumber) {
			super(handler);
			this.incomingnumber = incomingnumber;
		}

		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			deleteCallLog(incomingnumber);
			
			//��ɾ���˺��м�¼�� ��ע�����ݹ۲���
			getContentResolver().unregisterContentObserver(this);
		}
		
	}
	

}
