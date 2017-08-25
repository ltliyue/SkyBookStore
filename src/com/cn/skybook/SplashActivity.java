package com.cn.skybook;

import com.cn.finder.utils.PreferencesUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 欢迎界面
 * 
 * @author MaryLee
 * 
 */
public class SplashActivity extends Activity {
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		// mHandler.sendEmptyMessageDelayed(GO_LOGIN, 0);
		mHandler.sendEmptyMessage(GO_HOME);
	}

	private static final int GO_HOME = 100;
	private static final int GO_LOGIN = 200;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				PreferencesUtils.putString(SplashActivity.this, "ADDRESS", "192.168.0.108");
				intent = new Intent(SplashActivity.this, SearchActivity.class);
				// intent = new Intent(SplashActivity.this,
				// SearchUserActivity.class);
				startActivity(intent);
				finish();
				break;
			case GO_LOGIN:
				// Intent intent = new Intent(SplashActivity.this,
				// MainActivity.class);
				intent = new Intent(SplashActivity.this, SearchActivity.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};

}
