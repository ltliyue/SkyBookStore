package com.cn.skybook;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.finder.base.BaseActivty;
import com.cn.finder.utils.PreferencesUtils;
import com.cn.finder.utils.SharedPreferencesUtil;
import com.cn.skybook.url.ApiUrl;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SettingActivity extends BaseActivty {

	@ViewInject(R.id.back)
	ImageView back;
	@ViewInject(R.id.edit_address)
	EditText edit_address;
	@ViewInject(R.id.btn_save)
	TextView btn_save;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_setting);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {

		back.setOnClickListener(this);
		btn_save.setOnClickListener(this);
		edit_address.setText(PreferencesUtils.getString(this, "ADDRESS", "192.168.0.108"));
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {

		case R.id.back:
			finish();
			break;
		case R.id.btn_save:
			PreferencesUtils.putString(this, "ADDRESS", edit_address.getText().toString());
			// ApiUrl.urlString = edit_address.getText().toString();
			showToast("保存成功");
			finish();
			break;
		default:
			break;
		}
	}

}
