/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uchanneltv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uchanneltv.extra.UTVChannelConstant;

public class ActivityLiveStream extends Activity implements OnClickListener {

	/**
	 * TODO: Set the path variable to a streaming video URL or a local media file path.
	 */

	// private VideoView mVideoView;
	private ImageView imgLeft , imgRight , imgShare;
	private TextView txtCenter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		/*
		 * if (!LibsChecker.checkVitamioLibs(this)) return;
		 */
		setContentView(R.layout.videoview);
		imgLeft = (ImageView) findViewById(R.id.imgLeft);
		imgRight = (ImageView) findViewById(R.id.imgRight);
		imgShare = (ImageView) findViewById(R.id.imgShare);
		imgShare.setVisibility(View.GONE);
		txtCenter = (TextView) findViewById(R.id.txtCenter);
		txtCenter.setText("Live Streaming");

		/* mVideoView = (VideoView) findViewById(R.id.surface_view); */
		if (UTVChannelConstant.STREAM_URL == "") {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(ActivityLiveStream.this, "Please edit VideoViewDemo Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
			return;
		} else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
			/*
			 * mVideoView.setVideoPath(UTVChannelConstant.STREAM_URL);
			 * mVideoView.setMediaController(new MediaController(this)); mVideoView.requestFocus();
			 * mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			 * @Override public void onPrepared(MediaPlayer mediaPlayer) { // optional need Vitamio
			 * 4.0 mediaPlayer.setPlaybackSpeed(1.0f); } });
			 */
		}
		imgLeft.setOnClickListener(this);
		imgRight.setOnClickListener(this);
	}

	public void startPlay(View view) {
		if (!TextUtils.isEmpty(UTVChannelConstant.STREAM_URL)) {
			// mVideoView.setVideoPath(UTVChannelConstant.STREAM_URL);
		}
	}

	public void openVideo(View View) {
		// mVideoView.setVideoPath(UTVChannelConstant.STREAM_URL);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.imgLeft) {
			finish();
		}
		if (v.getId() == R.id.imgRight) {
			Intent WebIntent = new Intent(ActivityLiveStream.this, ActivityCallWebsite.class);
			startActivity(WebIntent);
		}
	}
}
