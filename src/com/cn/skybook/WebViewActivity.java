package com.cn.skybook;

import com.alibaba.fastjson.JSON;
import com.cn.finder.base.BaseActivty;
import com.cn.skybook.R;
import com.cn.skybook.model.BookInfo;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends BaseActivty {

	WebView webView;

	String wareID;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_webview);
		webView = (WebView) findViewById(R.id.web);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
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

				// System.out.println("-->"+bookInfo.getWdis());
				// Message message = new Message();
				// message.what = 0;
				// message.obj = bookInfo.getWdis();

				// mhandleer.sendMessage(message);
				WebSettings settings = webView.getSettings();
				settings.setUseWideViewPort(true);
				
				webView.setInitialScale(50);
				
				settings.setSupportZoom(false);  
				webView.loadDataWithBaseURL("about:blank", bookInfo.getWdis(), "text/html", "utf-8", null);

			}

		});
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

}
