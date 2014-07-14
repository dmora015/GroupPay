package com.example.grouppay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

public class LoginAsyncTask extends AsyncTask<String, Integer, String> {

	private Context context;
	private ProgressBar loginProgressBar;
	private static final String localIP = "192.168.0.12";
	
	private static final int USERNAME = 0;
	private static final int PASSWORD = 1;
	private static final String LOGIN_PAGE = "http://" + localIP + "/post_test.php";
	private static final String JSONSTRING_KEY = "JSONString";
	
	public LoginAsyncTask(Context context, JSONObject reader, ProgressBar loginProgressBar) {
		this.context = context;
		this.loginProgressBar = loginProgressBar;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		loginProgressBar.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected String doInBackground(String... params) {
		
		String username = (String)params[USERNAME];
		String password = (String)params[PASSWORD];
		
		// Access server
		try {
			String data = URLEncoder.encode("username", "UTF-8")
					+ "=" + URLEncoder.encode(username, "UTF-8")
					+ "&" + URLEncoder.encode("password", "UTF-8")
					+ "=" + URLEncoder.encode(password, "UTF-8");
			
			URL url = new URL(LOGIN_PAGE);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			// Read server response
			while((line = reader.readLine()) != null)
			{
				sb.append(line);
				
			}
			return sb.toString();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		
		loginProgressBar.setProgress(values[0]);
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		loginProgressBar.setVisibility(View.INVISIBLE);
		try {
			Intent startHomeActivityIntent = new Intent(
					context,
					HomeActivity.class);
			startHomeActivityIntent.putExtra(JSONSTRING_KEY, result);
			
			EasyToast.print(context.getApplicationContext(), result);
			
			context.startActivity(startHomeActivityIntent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
