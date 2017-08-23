package com.cn.skybook;

import com.cn.finder.base.MApplication;

public class MyApplication extends MApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
	}
}
