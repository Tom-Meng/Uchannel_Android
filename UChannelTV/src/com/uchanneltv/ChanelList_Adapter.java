package com.uchanneltv;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChanelList_Adapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<BeanSubCategory> mSubCategoryArrayList;

	public ChanelList_Adapter( Activity activity, Context ctx, ArrayList<BeanSubCategory> mSubCategoryArrayList ) {
		super();
		this.activity = activity;
		this.mSubCategoryArrayList = mSubCategoryArrayList;

	}

	private class ViewHolder {
		TextView txt_title;
		ImageView imgShare;
	}

	public int getCount() {
		return mSubCategoryArrayList.size();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		LayoutInflater inflater = activity.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.activity_sub_list_row, null);
			holder = new ViewHolder();
			holder.txt_title = (TextView) convertView.findViewById(R.id.txtSubRowTitle);
			holder.imgShare = (ImageView) convertView.findViewById(R.id.imgShare);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_title.setText(mSubCategoryArrayList.get(position).getTitlekey());
		holder.imgShare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Intent sendIntent = new Intent();
					sendIntent.setAction(Intent.ACTION_SEND);
					sendIntent.putExtra(Intent.EXTRA_TEXT, mSubCategoryArrayList.get(position).getTitlevalue());
					sendIntent.setType("text/plain");
					activity.startActivity(sendIntent);
				} catch (Exception e) {
					// TODO: handle exception
					Log.e("log_tag", "" + e);
				}
			}
		});

		return convertView;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return mSubCategoryArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
