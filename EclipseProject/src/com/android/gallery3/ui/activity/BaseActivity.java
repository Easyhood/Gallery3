package com.android.gallery3.ui.activity;

import com.android.gallery3.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseActivity extends FragmentActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
	}
	
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
	}

}
