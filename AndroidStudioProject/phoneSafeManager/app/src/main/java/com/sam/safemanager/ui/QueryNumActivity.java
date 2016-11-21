/**
 * 
 */
package com.sam.safemanager.ui;

import com.sam.safemanager.R;
import com.sam.safemanager.engine.NumberAddressService;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Sam
 * @date 2013-5-6
 * @weibo:��ũ����ɣ
 */
public class QueryNumActivity extends Activity {
	private EditText etNum;
	private Button btnQuery;
	private TextView tvResult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_number);
		
		etNum = (EditText) this.findViewById(R.id.et_query_number);
		btnQuery = (Button) this.findViewById(R.id.btn_query);
		tvResult = (TextView) findViewById(R.id.tv_query_number_address);
		tvResult.setText("");
		btnQuery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				query();
			}
		});
		
		etNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//TODO ��ѯ
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//TODO ��ѯ
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				query();
				
			}
		});
		
	}
	
	private void query(){
		// �жϺ����Ƿ�Ϊ�� 
				String number =  etNum.getText().toString().trim();
				if(TextUtils.isEmpty(number)){
			        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			        etNum.startAnimation(shake);
				}else{
					//�����ݿ� ��ѯ���������
				   String address =	NumberAddressService.getAddress(number);
				   if(address != number)
					   tvResult.setText("��������Ϣ "+ address);
				   else
					   tvResult.setText("δ�鵽��������Ϣ");
					
				}
	}

}
