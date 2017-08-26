package com.cn.skybook;

import com.alibaba.fastjson.JSON;
import com.cn.finder.base.BaseActivty;
import com.cn.skybook.model.BookInfo;
import com.cn.skybook.view.URLImageGetter2;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class SearchInfoActivity extends BaseActivty {

	String wareID;

	TextView text1;

	URLImageGetter2 reviewImgGetter;

	private Handler mhandleer = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {

				// 实例化URLImageGetter类
				reviewImgGetter = new URLImageGetter2(SearchInfoActivity.this, text1);
				text1.setText(Html.fromHtml(msg.obj.toString(), reviewImgGetter, null));

			}
		};
	};

	@Override
	protected void initView() {
		setContentView(R.layout.activity_searchinfo);
		text1 = (TextView) findViewById(R.id.text1);
	}

	@Override
	protected void initData() {
		wareID = getIntent().getStringExtra("wareid");
		String infoHosts = "https://item.m.jd.com/ware/detail.json?wareId=" + wareID;

		loadData(HttpMethod.GET, infoHosts, null, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {

				BookInfo bookInfo = JSON.parseObject(arg0.result, BookInfo.class);

				System.out.println("-->"+bookInfo.getWdis());
				Message message = new Message();
				message.what = 0;
				message.obj = bookInfo.getWdis();

				mhandleer.sendMessage(message);

			}

		});
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

}
