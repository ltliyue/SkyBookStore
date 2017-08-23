package com.cn.skybook;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.cn.finder.base.BaseActivty;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends BaseActivty {

	@ViewInject(R.id.lin_dw)
	LinearLayout lin_dw;
	@ViewInject(R.id.lin_cd)
	LinearLayout lin_cd;
	@ViewInject(R.id.lin_yhq)
	LinearLayout lin_yhq;
	@ViewInject(R.id.lin_tq)
	LinearLayout lin_tq;

	Intent intent;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		lin_cd.setOnClickListener(this);
		lin_dw.setOnClickListener(this);
		lin_yhq.setOnClickListener(this);
		lin_tq.setOnClickListener(this);
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
//		case R.id.lin_cd:
//			intent = new Intent(MainActivity.this, LoginActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.lin_dw:
//			intent = new Intent(MainActivity.this, MapActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.lin_yhq:
//			intent = new Intent(MainActivity.this, CheapActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.lin_tq:
//			intent = new Intent(MainActivity.this, WeatherActivity.class);
//			startActivity(intent);
//			break;
		default:
			break;
		}
	}
}
