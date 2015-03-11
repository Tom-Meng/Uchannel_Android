package com.uchanneltv;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ActivitySplash extends Activity implements OnClickListener {
	private ImageView imgSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		imgSplash = (ImageView) findViewById(R.id.imgSplash);
		imgSplash.setOnClickListener(this);
		CallHomeActivity();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.imgSplash) {
			Intent WebIntent = new Intent(ActivitySplash.this, ActivityCallWebsite.class);
			startActivity(WebIntent);
		}
	}

	private void CallHomeActivity() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Intent infoIntent = new Intent(ActivitySplash.this, ActivityHome.class);
				startActivity(infoIntent);
				finish();
			}
		}, 3000);
	}
}
