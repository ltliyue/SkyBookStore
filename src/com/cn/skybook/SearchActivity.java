package com.cn.skybook;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.cn.finder.base.BaseActivty;
import com.cn.finder.pulltorefresh.PullToRefreshBase;
import com.cn.finder.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.cn.finder.pulltorefresh.PullToRefreshListView;
import com.cn.skybook.model.Book;
import com.cn.skybook.model.BookSearchResult;
import com.cn.skybook.model.WareList;
import com.cn.skybook.utils.TimeTools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

	BookWebListAdapter bookListAdapter;
	
	ArrayList<WareList> wareLists;

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
//				 Intent intent = new Intent(ct, SearchInfoActivity.class);
				 Intent intent = new Intent(ct, WebViewActivity.class);
				 intent.putExtra("wareid", wareLists.get(position).getWareId());
				 ct.startActivity(intent);

			}
		});
	}

	private void initBBSListData(){
		
		String url = "https://so.m.jd.com/ware/search.action?keyword="+code;

		loadData(HttpMethod.GET, url, null, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				if (arg0.result != null) {
					Document doc = Jsoup.parse(arg0.result);

			        Elements scripts = doc.select("script");

			        String data = null;
			        for (Element script : scripts) {
			            if (script.html().contains("jsArgs['search'] =")) {
			                Pattern p = Pattern.compile("searchData:(.*), abtestForUpToSaving");
			                Matcher m = p.matcher(script.toString());
			                while (m.find()) {
			                    data = m.group(1);
			                }
			            }
			        }
			        if (data != null) {
			        	com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(data);
			            String wareList  = jsonObject.getString("wareList");

			        	BookSearchResult bookSearchResult = JSON.parseObject(wareList,BookSearchResult.class);
						
			        	wareLists = bookSearchResult.getWareList();
			        	
						bookListAdapter = new BookWebListAdapter(ct, wareLists);
						bbs_lv.getRefreshableView().setAdapter(bookListAdapter);
						lvSet();
			        }
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
