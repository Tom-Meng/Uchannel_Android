package com.uchanneltv;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.uchanneltv.extra.UTVChannelConstant;
import com.uchanneltv.extra.XMLParser;

public class ActivitySubCategory extends Activity implements OnClickListener {

	private ListView lvSubCategory;
	private ArrayList<BeanSubCategory> mArrayList = new ArrayList<BeanSubCategory>();;
	private String SelectedCat = "";
	private ImageView imgLeft, imgRight, imgShare;
	private TextView txtCenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub);
		SetControl();
	}

	private void SetControl() {
		lvSubCategory = (ListView) findViewById(R.id.lvSubCategory);
		imgLeft = (ImageView) findViewById(R.id.imgLeft);
		imgRight = (ImageView) findViewById(R.id.imgRight);
		imgShare = (ImageView) findViewById(R.id.imgShare);
		txtCenter = (TextView) findViewById(R.id.txtCenter);
		imgShare.setVisibility(View.GONE);
		imgLeft.setOnClickListener(this);
		imgRight.setOnClickListener(this);

		SelectedCat = getIntent().getStringExtra("SelectedCat");

		mArrayList.clear();
		parseXMLDemo(SelectedCat);

		lvSubCategory.setOnItemClickListener(new OnItemClickListener() {
			// argument position gives the index of item which is clicked
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

				String mTitleValue = mArrayList.get(position).getTitlevalue();

				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mTitleValue));
				startActivity(browserIntent);

			}
		});
	}

	private void parseXMLDemo(String SelectedCat) {
		String ParseXML_URL = null;
		AssetManager assetManager = getBaseContext().getAssets();
		try {
			if (getString(R.string.txt_life).equalsIgnoreCase(SelectedCat)) {
				ParseXML_URL = UTVChannelConstant.LIFE_FILE;
				txtCenter.setText(getString(R.string.txt_life));
			}
			if (getString(R.string.txt_news).equalsIgnoreCase(SelectedCat)) {
				ParseXML_URL = UTVChannelConstant.NEWS_FILE;
				txtCenter.setText(getString(R.string.txt_news));
			}
			if (getString(R.string.txt_finance).equalsIgnoreCase(SelectedCat)) {
				ParseXML_URL = UTVChannelConstant.FINANCE_FILE;
				txtCenter.setText(getString(R.string.txt_finance));
			}
			if (getString(R.string.txt_events).equalsIgnoreCase(SelectedCat)) {
				ParseXML_URL = UTVChannelConstant.EVENT_FILE;
				txtCenter.setText(getString(R.string.txt_events));
			}
			if (getString(R.string.txt_love).equalsIgnoreCase(SelectedCat)) {
				ParseXML_URL = UTVChannelConstant.TRUE_LOVE;
				txtCenter.setText(getString(R.string.txt_love));
			}

			new getXML().execute(ParseXML_URL);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class getXML extends AsyncTask<String, String, String> {

		String xml = null;
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			progressDialog = new ProgressDialog(ActivitySubCategory.this);
			progressDialog.setMessage("Please wait...");
			progressDialog.setIndeterminate(true);
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {

			XMLParser parser = new XMLParser();
			String xml = parser.getXmlFromUrl(params[0]); // getting XML

			JSONObject jsonObj = null;
			try {
				jsonObj = XML.toJSONObject(xml);
			} catch (JSONException e) {
				Log.e("JSON exception", e.getMessage());
				e.printStackTrace();
			}

			try {
				JSONObject plist = jsonObj.getJSONObject("plist");
				JSONObject dict = plist.getJSONObject("dict");

				JSONArray key = dict.getJSONArray("key");
				JSONArray string = dict.getJSONArray("string");

				for (int i = 0; i < key.length(); i++) {

					BeanSubCategory mBeanSubCategory = new BeanSubCategory();
					mBeanSubCategory.setTitlekey(key.getString(i));
					mBeanSubCategory.setTitlevalue(string.getString(i));

					mArrayList.add(mBeanSubCategory);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.cancel();
			ChanelList_Adapter cAdapter = new ChanelList_Adapter(ActivitySubCategory.this, getApplicationContext(), mArrayList);
			lvSubCategory.setAdapter(cAdapter);
			cAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.imgLeft) {
			finish();
		}
		if (v.getId() == R.id.imgRight) {
			Intent WebIntent = new Intent(ActivitySubCategory.this, ActivityCallWebsite.class);
			startActivity(WebIntent);
		}
	}
}
