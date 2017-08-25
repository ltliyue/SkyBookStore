package com.cn.skybook;

import java.util.ArrayList;
import java.util.List;

import com.cn.finder.base.MyBaseAdapter;
import com.cn.skybook.model.Book;
import com.cn.skybook.model.WareList;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BookWebListAdapter extends MyBaseAdapter<WareList, ListView> {

	public BookWebListAdapter(Context context, List<WareList> list) {
		super(context, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_reply_post, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.isbn = (TextView) convertView.findViewById(R.id.isbn);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.author = (TextView) convertView.findViewById(R.id.author);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			holder.publisher = (TextView) convertView.findViewById(R.id.publisher);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Picasso.with(context).load(list.get(position).getImageurl()).into(holder.image);
		holder.isbn.setText("条码：XXX");
		holder.name.setText(list.get(position).getWname());
		holder.author.setText("折扣：" + list.get(position).getAuthor());
		holder.price.setText("卖价：" + list.get(position).getJdPrice());
		holder.publisher.setText(list.get(position).getShopName());

		return convertView;

	}

	class ViewHolder {
		public ImageView image;
		public TextView isbn;
		public TextView name;
		public TextView author;
		public TextView price;
		public TextView publisher;
	}
}