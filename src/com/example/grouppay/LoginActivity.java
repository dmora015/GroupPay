package com.example.grouppay;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


public class LoginActivity extends Activity {
	
	private static EditText username;
	private static EditText password;
	private static Button login;
	private static ProgressBar loginProgressBar;
	
	private static JSONObject reader;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        username = (EditText)findViewById(R.id.usernameEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        login = (Button)findViewById(R.id.loginButton);
        loginProgressBar = (ProgressBar)findViewById(R.id.loginProgressBar);
        
        login.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		String usernameString = username.getText().toString();
        		String passwordString = password.getText().toString();
        		
        		new LoginAsyncTask(LoginActivity.this, reader, loginProgressBar).execute(usernameString, passwordString);
        	}
        	
        });
        
        
        
    }
}
