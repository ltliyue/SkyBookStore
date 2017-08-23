package com.cn.skybook;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cn.finder.base.BaseActivty;
import com.cn.finder.base.MyBaseAdapter;
import com.cn.finder.pulltorefresh.PullToRefreshBase;
import com.cn.finder.pulltorefresh.PullToRefreshBase.OnRefreshListener;
import com.cn.finder.pulltorefresh.PullToRefreshListView;
import com.cn.skybook.model.Book;
import com.cn.skybook.url.ApiUrl;
import com.cn.skybook.utils.TimeTools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

public class SearchUserActivity extends BaseActivty {

	@ViewInject(R.id.edit_search_user)
	EditText edit_search_user;
	@ViewInject(R.id.cancel_user)
	TextView cancel_user;

	@ViewInject(R.id.bbs_lv)
	PullToRefreshListView bbs_lv;

	List<Book> books;
	Intent mIntent;
	private int page = 1;
	private boolean loadMore = false;

	private ArrayAdapter<String> sugAdapter = null;

	String username = "";

	BookListAdapter bookListAdapter;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_search_user);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		edit_search_user.setFocusable(true);
		// openInput();
		cancel_user.setOnClickListener(this);

		getMyData();

	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {

		case R.id.cancel_user:
			username = edit_search_user.getText().toString();
			if (TextUtils.isEmpty(username)) {
				showToast("请输入会员名");
				return;
			}
			bbs_lv.doPullRefreshing(true, 500);
			break;
		default:
			break;
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
				loadMore = false;
				page = 1;
				initBBSListData(loadMore);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// 加载更多...
				if (books.size() < 10) {
					bbs_lv.setHasMoreData(false, null);
				} else {
					bbs_lv.setHasMoreData(true, null);
					loadMore = true;
					page = page + 1;
					initBBSListData(loadMore);
				}
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

	private void initBBSListData(final boolean loadMore) {
		RequestParams requestParams = new RequestParams();
		requestParams.addQueryStringParameter("code", username);
		requestParams.addQueryStringParameter("page", page + "");
		// System.out.println(MUrl.forumlistUrl+":::"+requestParams.toString());
		loadData(HttpMethod.GET, ApiUrl.search_user_by_name, requestParams, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
//				try {
					if (arg0.result != null) {
						showToast("查无此书"+arg0.result);
//						JSONObject jsonObject = new JSONObject(arg0.result);
//						List<Book> new_books = JSON.parseArray(jsonObject.getString("data"), Book.class);
//						if (new_books == null || new_books.size() == 0) {
//							bookListAdapter.notifyDataSetChanged();
//							lvSet();
//							showToast("查无此书");
//							return;
//						}
//						if (!loadMore) {
//							books = new_books;
//							bookListAdapter = new BookListAdapter(ct, books);
//							bbs_lv.getRefreshableView().setAdapter(bookListAdapter);
//
//							if (books.size() < 10) {
//								bbs_lv.setHasMoreData(false, null);
//							} else {
//								bbs_lv.setHasMoreData(true, null);
//							}
//						} else {
//							books.addAll(new_books);
//							bookListAdapter.notifyDataSetChanged();
//
//							if (new_books.size() < 10) {
//								bbs_lv.setHasMoreData(false, null);
//							} else {
//								bbs_lv.setHasMoreData(true, null);
//							}
//						}

						lvSet();
					} else {
						showToast("没有数据");
					}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("未开启服务");
			}
		});
	}

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
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText("条码：" + list.get(position).getBarCode());
			holder.post_detail_title.setText(list.get(position).getName());
			holder.dingjia.setText("定价：" + list.get(position).getPrice() + "");
			holder.zhekou.setText("折扣：" + list.get(position).getDtlDiscount() + "");
			holder.maijia.setText("卖价：" + list.get(position).getDtlDscntPrice() + "");
			// holder.value.setTag(list.get(position).getKey());

			return convertView;

		}

		class ViewHolder {
			public TextView name;
			public TextView post_detail_title;
			public TextView dingjia;
			public TextView zhekou;
			public TextView maijia;
		}
	}
}
