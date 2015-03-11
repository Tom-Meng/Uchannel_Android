package com.uchanneltv;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.uchanneltv.extra.SessionManager;
import com.uchanneltv.extra.UTVChannelConstant;

public class ActivityHome extends Activity implements OnClickListener {
	private Button btnDailyNews, btnFinance, btnLOV, btnEvent, btnTrueLove;
	private boolean LoginStatus = false;
	private TextView txtSignInOrLiveStream, txtVisitWebsite;
	private SessionManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		SetControl();
	}

	private void SetControl() {

		manager = new SessionManager(getApplicationContext());
		btnDailyNews = (Button) findViewById(R.id.btnDailyNews);
		btnFinance = (Button) findViewById(R.id.btnFinance);
		btnLOV = (Button) findViewById(R.id.btnLOV);
		btnEvent = (Button) findViewById(R.id.btnEvent);
		btnTrueLove = (Button) findViewById(R.id.btnTrueLove);
		txtSignInOrLiveStream = (TextView) findViewById(R.id.txtSignInOrLiveStream);
		txtVisitWebsite = (TextView) findViewById(R.id.txtVisitWebsite);
		LoginStatus = manager.Get_Boolean_Detail("IsLogin");

		if (!LoginStatus) {
			txtSignInOrLiveStream.setBackgroundResource(R.drawable.signin);
		} else {
			txtSignInOrLiveStream.setBackgroundResource(R.drawable.btn_live_streaming);
		}

		btnDailyNews.setOnClickListener(this);
		btnFinance.setOnClickListener(this);
		btnLOV.setOnClickListener(this);
		btnEvent.setOnClickListener(this);
		btnTrueLove.setOnClickListener(this);
		txtSignInOrLiveStream.setOnClickListener(this);
		txtVisitWebsite.setOnClickListener(this);

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
		if (v.getId() == R.id.btnDailyNews) {
			CallSubList(getResources().getString(R.string.txt_news));
		}
		if (v.getId() == R.id.btnFinance) {
			CallSubList(getResources().getString(R.string.txt_finance));
		}
		if (v.getId() == R.id.btnLOV) {
			CallSubList(getResources().getString(R.string.txt_life));
		}
		if (v.getId() == R.id.btnEvent) {
			CallSubList(getResources().getString(R.string.txt_events));
		}
		if (v.getId() == R.id.btnTrueLove) {
			CallSubList(getResources().getString(R.string.txt_love));
		}
		if (v.getId() == R.id.txtSignInOrLiveStream) {

			if (!LoginStatus) {
				Intent loginIntent = new Intent(ActivityHome.this, ActivityLogin.class);
				startActivityForResult(loginIntent, UTVChannelConstant.PICK_LOGIN);
			} else {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UTVChannelConstant.STREAM_URL));
				startActivity(browserIntent);
			}
		}
		if (v.getId() == R.id.txtVisitWebsite) {
			Intent websiteIntent = new Intent(ActivityHome.this, ActivityCallWebsite.class);
			startActivity(websiteIntent);
		}

	}

	private void CallSubList(String btnValue) {
		Intent listIntent = new Intent(ActivityHome.this, ActivitySubCategory.class);
		listIntent.putExtra("SelectedCat", btnValue);
		startActivity(listIntent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == UTVChannelConstant.PICK_LOGIN) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					LoginStatus = extras.getBoolean("LoginStatus");
					if (!LoginStatus) {
						txtSignInOrLiveStream.setBackgroundResource(R.drawable.signin);
					} else {
						txtSignInOrLiveStream.setBackgroundResource(R.drawable.btn_live_streaming);
					}
					Log.v("log_tag", "" + LoginStatus);
				}
			}
		}
	}
}
