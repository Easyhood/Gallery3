package com.android.gallery3.utils;

import java.lang.annotation.Target;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 计算状态栏高度及调用沉浸式状态栏的方法
 * @author qi.guan
 *
 */
public class ImmersedStatusUtils {
	/**
	 * 在setContentView之后调用
	 * 头部控件的ViewGroup若为null，整个界面将与状态栏重叠
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void initAfterSetContentView(Activity activity,View titleViewGroup){
		if(activity == null){
			return;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			
			Window window = activity.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		if(titleViewGroup == null){
			return;
		}
		//设置头部空间ViewGroup的PaddingTop,防止界面与状态栏重叠
		int statusBarHeight = getStatusBarHeight(activity);
		titleViewGroup.setPadding(0,statusBarHeight,0,0);
	}
	
	
	/**
	 * 获取状态栏高度
	 */
	public static int getStatusBarHeight(Context context){
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height","dimen","android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
			
		}
		return result;
	}
	
}
