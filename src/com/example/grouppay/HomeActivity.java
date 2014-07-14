package com.example.grouppay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

	private static final String USERNAME_KEY = "login";
	private static final String PASSWORD_KEY = "password";
	private static final String FNAME_KEY = "fname";
	private static final String LNAME_KEY = "lname";
	private static final String EMAIL_KEY = "email";
	private static final String PIC_KEY = "pic";
	private static final String JSONSTRING_KEY = "JSONString";
	
	private static TextView usernameTextView;
	private static TextView passwordTextView;
	private static TextView fnameTextView;
	private static TextView lnameTextView;
	private static TextView emailTextView;
	private static TextView picTextView;
	
	private static Intent userInfoIntent;
	
	private JSONObject JSONreader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		usernameTextView = (TextView)findViewById(R.id.usernameTextView);
		passwordTextView = (TextView)findViewById(R.id.passwordTextViewHome);
		fnameTextView = (TextView)findViewById(R.id.fnameTextView);
		lnameTextView = (TextView)findViewById(R.id.lnameTextView);
		emailTextView = (TextView)findViewById(R.id.emailTextView);
		picTextView = (TextView)findViewById(R.id.picTextView);
		
		userInfoIntent = this.getIntent();
		this.saveIntentData(userInfoIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void saveIntentData(Intent intent) {
		try {
			JSONreader = new JSONObject(intent.getCharSequenceExtra(JSONSTRING_KEY).toString());
			
			JSONArray UserArray = JSONreader.getJSONArray("User");
			JSONObject User = UserArray.getJSONObject(0);
			
			String usernameString = User.getString("login");
			usernameTextView.setText(usernameString);
			
			String passwordString = User.getString("password");
			passwordTextView.setText(passwordString);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
