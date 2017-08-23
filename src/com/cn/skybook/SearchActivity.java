package com.cn.skybook;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cn.finder.base.BaseActivty;
import com.cn.finder.base.MyBaseAdapter;
import com.cn.finder.pulltorefresh.PullToRefreshBase;
import com.cn.finder.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.cn.finder.pulltorefresh.PullToRefreshListView;
import com.cn.finder.utils.PreferencesUtils;
import com.cn.skybook.model.Book;
import com.cn.skybook.url.ApiUrl;
import com.cn.skybook.utils.TimeTools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SearchActivity extends BaseActivty {

	@ViewInject(R.id.back)
	ImageView back;
	@ViewInject(R.id.edit_search)
	EditText edit_search;
	@ViewInject(R.id.cancel)
	TextView cancel;
	@ViewInject(R.id.title)
	TextView title;

	@ViewInject(R.id.btn_search)
	TextView btn_search;

	@ViewInject(R.id.bbs_lv)
	PullToRefreshListView bbs_lv;

	List<Book> books;
	Intent mIntent;
	private int page = 1;
	private boolean loadMore = false;

	String code = "";
	String url = "";

	BookListAdapter bookListAdapter;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_search);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		edit_search.setFocusable(true);
		// openInput();

		back.setOnClickListener(this);
		cancel.setOnClickListener(this);
		btn_search.setOnClickListener(this);
		title.setOnClickListener(this);
		getMyData();

	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {

		case R.id.back:
			Intent intent = new Intent();
			intent.setClass(this, MipcaActivityCapture.class);
			startActivityForResult(intent, 0);
			break;
		case R.id.title:
			Intent intent2 = new Intent(this, SettingActivity.class);
			startActivity(intent2);
			break;
		case R.id.cancel:
			code = edit_search.getText().toString();
			if (TextUtils.isEmpty(code)) {
				showToast("请输入条形码");
				return;
			}
			hideInputType();
			bbs_lv.doPullRefreshing(true, 500);
			break;
		case R.id.btn_search:
			showToast("开发中...");
			// Intent intent_btn_search = new Intent(this,
			// SearchUserActivity.class);
			// startActivityForResult(intent_btn_search, 0);
			break;

		default:
			break;
		}
	}

	private void hideInputType() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {
				// InputMethodManager inputMethodManager = (InputMethodManager)
				// getSystemService(Context.INPUT_METHOD_SERVICE);
				// inputMethodManager.toggleSoftInput(0,
				// InputMethodManager.HIDE_NOT_ALWAYS);

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edit_search.getWindowToken(), 0);
			}
		}, 100);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				code = intent.getStringExtra("RESULT");
				edit_search.setText(code);
				showToast(code);
				bbs_lv.doPullRefreshing(true, 500);
			} else if (resultCode == RESULT_CANCELED) {
			}
		} else {
			return;
		}
	}

	private void getMyData() {

		bbs_lv.setPullLoadEnabled(false);
		// 滚动到底自动加载可用
		bbs_lv.setScrollLoadEnabled(true);
		// bbs_lv.doPullRefreshing(true, 500);
		// 得到实际的ListView 设置点击
		// 设置下拉刷新的listener
		bbs_lv.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				code = edit_search.getText().toString();
				if (TextUtils.isEmpty(code)) {
					lvSet();
					return;
				}
				loadMore = false;
				page = 1;
				initBBSListData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// 加载更多...
				loadMore = true;
				page = page + 1;
				initBBSListData();
			}
		});

		bbs_lv.getRefreshableView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Intent intent = new Intent(ct, BBSDetailsActivity.class);
				// intent.putExtra("tid", posts.get(position).getTid());
				// ct.startActivity(intent);

			}
		});
	}

	private void initBBSListData(){
		
		String url = "https://so.m.jd.com/ware/search.action?keyword="+code;

		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0.result != null) {
					LogUtils.i(arg0.result);
				}
			}
			
		});
		
	}
//	private void initBBSListData(final boolean loadMore) {
//		RequestParams requestParams = new RequestParams();
//		requestParams.addQueryStringParameter("code", code);
//		// System.out.println(MUrl.forumlistUrl+":::"+requestParams.toString());
//		url = getResources().getString(R.string.host);
//		// url = String.format(url, PreferencesUtils.getString(this,
//		// "ADDRESS"));
//		if (code.length() != 13) {
//			url = String.format(url, PreferencesUtils.getString(this, "ADDRESS")) + ApiUrl.search_book_by_name;
//			requestParams.addQueryStringParameter("page", page + "");
//		} else {
//			url = String.format(url, PreferencesUtils.getString(this, "ADDRESS")) + ApiUrl.search_book;
//		}
//		LogUtils.e("aaa" + url);
//		loadData(HttpMethod.GET, url, requestParams, new RequestCallBack<String>() {
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				try {
//					if (arg0.result != null) {
//						JSONObject jsonObject = new JSONObject(arg0.result);
//						List<Book> new_books = JSON.parseArray(jsonObject.getString("data"), Book.class);
//						if (new_books == null || new_books.size() == 0) {
//							if (bookListAdapter != null) {
//								bookListAdapter.notifyDataSetChanged();
//							}
//							lvSet();
//							showToast("查无此书");
//							return;
//						}
//						if (!loadMore) {
//							books = new_books;
//							bookListAdapter = new BookListAdapter(ct, books);
//							bbs_lv.getRefreshableView().setAdapter(bookListAdapter);
//							lvSet();
//							if (books.size() < 10) {
//								bbs_lv.setHasMoreData(false, null);
//							} else {
//								bbs_lv.setHasMoreData(true, null);
//							}
//						} else {
//							books.addAll(new_books);
//							bookListAdapter.notifyDataSetChanged();
//							if (new_books.size() < 10) {
//								bbs_lv.setHasMoreData(false, null);
//							} else {
//								bbs_lv.setHasMoreData(true, null);
//							}
//						}
//
//					} else {
//						showToast("没有数据");
//					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				showToast("未开启服务");
//			}
//		});
//	}

	private void lvSet() {
		bbs_lv.onPullUpRefreshComplete();
		bbs_lv.onPullDownRefreshComplete();
		bbs_lv.setLastUpdatedLabel(TimeTools.getStringDate());
	}

	class BookListAdapter extends MyBaseAdapter<Book, ListView> {

		public BookListAdapter(Context context, List<Book> list) {
			super(context, list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.item_reply_post, null);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.post_detail_title = (TextView) convertView.findViewById(R.id.post_detail_title);
				holder.dingjia = (TextView) convertView.findViewById(R.id.dingjia);
				holder.zhekou = (TextView) convertView.findViewById(R.id.zhekou);
				holder.maijia = (TextView) convertView.findViewById(R.id.maijia);

				holder.gonghuo = (TextView) convertView.findViewById(R.id.gonghuo);
				holder.yuanshi = (TextView) convertView.findViewById(R.id.yuanshi);
				holder.xiancun = (TextView) convertView.findViewById(R.id.xiancun);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText("条码：" + list.get(position).getBarCode());
			holder.post_detail_title.setText(list.get(position).getName());
			holder.dingjia.setText("定价：" + list.get(position).getPrice() + "");
			holder.zhekou.setText("折扣：" + list.get(position).getDtlDiscount() + "%");
			holder.maijia.setText("卖价：" + list.get(position).getDtlDscntPrice() + "");

			holder.gonghuo.setText("供货商：" + list.get(position).getStockName());
			holder.yuanshi.setText("原始数量：" + list.get(position).getOriginalAmnt() + "");
			holder.xiancun.setText("现存数量：" + list.get(position).getCurrentAmnt() + "");
			// holder.value.setTag(list.get(position).getKey());

			return convertView;

		}

		class ViewHolder {
			public TextView name;
			public TextView post_detail_title;
			public TextView dingjia;
			public TextView zhekou;
			public TextView maijia;

			public TextView gonghuo;
			public TextView yuanshi;
			public TextView xiancun;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		exitBy2Click();
	}

	/**
	 * 双击退出函数
	 */
	private static Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
//			AppManager.getAppManager().AppExit(this);
			finish();
		}
	}
}
