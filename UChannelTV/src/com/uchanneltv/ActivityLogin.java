package com.uchanneltv;

import com.uchanneltv.extra.SessionManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends Activity implements OnClickListener {

	private Button btnLogin;
	private EditText edtLoginUname;
	private Spannable WordtoSpan;
	private TextView txtSignup;
	private SessionManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		SetControl();
		SetSignUpText();
		btnLogin.setOnClickListener(this);
		// txtSignup.setOnClickListener(this);

	}

	private void SetControl() {
		manager = new SessionManager(getApplicationContext());
		edtLoginUname = (EditText) findViewById(R.id.edtLoginUname);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		txtSignup = (TextView) findViewById(R.id.txtSignup);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btnLogin) {
			DoLoginAuthentication();
		}
		if (v.getId() == R.id.txtSignup) {
			DoSignUp();
		}
	}

	private void SetSignUpText() {
		WordtoSpan = new SpannableString(getString(R.string.txt_signup));
		WordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.dark_coffe)), 0, 10, 0);
		WordtoSpan.setSpan(new ForegroundColorSpan(Color.WHITE), 11, getString(R.string.txt_signup).length(), 0);
		txtSignup.setText(WordtoSpan);
	}

	private void DoLoginAuthentication() {

		String UPIN = edtLoginUname.getText().toString().trim();
		// call api
		if (UPIN.equalsIgnoreCase("1177")) {
			manager.Set_Boolean_Detail("IsLogin", true);
			Intent backLogin = new Intent(ActivityLogin.this, ActivityHome.class);
			backLogin.putExtra("LoginStatus", true);
			setResult(RESULT_OK, backLogin);
			finish();
		} else {
			Show_Ok("UTVchannel", "Sorry.!!! You have type wrong PIN number. Try again.");
			edtLoginUname.setText("");
			UPIN = "";
		}

	}

	private void DoSignUp() {
		Intent signupIntent = new Intent(ActivityLogin.this, ActivityCallWebsite.class);
		startActivity(signupIntent);
		finish();
	}

	@Override
	public void onBackPressed() {
		setResult(RESULT_CANCELED, null);
		finish();
	}

	public void Show_Ok(final String title, final String msg) {
		AlertDialog.Builder adb = new AlertDialog.Builder(ActivityLogin.this);
		adb.setTitle(title);
		adb.setMessage(msg);
		adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		adb.show();
	}
}
