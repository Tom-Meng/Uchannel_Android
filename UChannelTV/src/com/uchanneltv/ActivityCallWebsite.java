package com.uchanneltv;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.uchanneltv.custom.utvChannelBrowser;
import com.uchanneltv.extra.UTVChannelConstant;

public class ActivityCallWebsite extends Activity {
	private WebView wvCallWebsite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_website);

		wvCallWebsite = (WebView) findViewById(R.id.wvCallWebsite);

		wvCallWebsite.setWebViewClient(new utvChannelBrowser());
		wvCallWebsite.getSettings().setLoadsImagesAutomatically(true);
		wvCallWebsite.getSettings().setJavaScriptEnabled(true);
		wvCallWebsite.loadUrl(UTVChannelConstant.WEBSITE_URL);
	}

}
