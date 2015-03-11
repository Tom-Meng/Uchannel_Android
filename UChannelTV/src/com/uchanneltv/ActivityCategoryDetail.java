package com.uchanneltv;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.uchanneltv.custom.utvChannelBrowser;

public class ActivityCategoryDetail extends Activity implements OnClickListener {
	private WebView wvCategoryDetail;
	private String LoadUrl = "" , TitleKey = "" , RootKey = "";
	private TextView txtCenter;
	private ImageView imgLeft , imgRight , imgShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categorydetail);
		wvCategoryDetail = (WebView) findViewById(R.id.wvCategoryDetail);
		imgLeft = (ImageView) findViewById(R.id.imgLeft);
		imgRight = (ImageView) findViewById(R.id.imgRight);
		imgShare = (ImageView) findViewById(R.id.imgShare);
		txtCenter = (TextView) findViewById(R.id.txtCenter);
		imgShare.setVisibility(View.VISIBLE);
		LoadUrl = getIntent().getStringExtra("TitleValue");
		TitleKey = getIntent().getStringExtra("TitleKey");
		RootKey = getIntent().getStringExtra("RootKey");

		txtCenter.setText(TitleKey);

		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(LoadUrl));
		startActivity(browserIntent);
		/*
		 * wvCategoryDetail.setWebViewClient(new utvChannelBrowser());
		 * wvCategoryDetail.getSettings().setLoadsImagesAutomatically(true);
		 * wvCategoryDetail.getSettings().setJavaScriptEnabled(true);
		 * wvCategoryDetail.loadUrl(LoadUrl);
		 */

		imgLeft.setOnClickListener(this);
		imgRight.setOnClickListener(this);
		imgShare.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.imgLeft) {
			finish();
		}
		if (v.getId() == R.id.imgRight) {
			Intent WebIntent = new Intent(ActivityCategoryDetail.this, ActivityCallWebsite.class);
			startActivity(WebIntent);

		}
		if (v.getId() == R.id.imgShare) {
			try {
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, LoadUrl);
				sendIntent.setType("text/plain");
				startActivity(sendIntent);
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log_tag", "" + e);
			}

		}
	}
}
