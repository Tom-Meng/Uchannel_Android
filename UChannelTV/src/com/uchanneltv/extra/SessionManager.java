package com.uchanneltv.extra;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
	// Shared Preferences
	SharedPreferences obj_Preferences;

	// Editor for Shared preferences
	Editor obj_Editor;

	// Context
	Context obj_Context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "utvchannel_pref";

	// Constructor
	public SessionManager( Context context ) {
		this.obj_Context = context;
		obj_Preferences = obj_Context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
	}

	public void Set_Boolean_Detail(String key, Boolean value) {
		obj_Editor = obj_Preferences.edit();
		obj_Editor.putBoolean(key, value);
		// commit changes
		obj_Editor.commit();
	}

	public boolean Get_Boolean_Detail(String key) {
		obj_Editor = obj_Preferences.edit();
		boolean status = obj_Preferences.getBoolean(key, false);
		obj_Editor.commit();
		return status;
	}

	public void logoutUser() {
		// Clearing all data from Shared Preferences
		obj_Editor.clear();
		obj_Editor.commit();
	}

}
