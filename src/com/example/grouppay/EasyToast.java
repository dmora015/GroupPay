package com.example.grouppay;

import android.content.Context;
import android.widget.Toast;

public class EasyToast {
		
	public static void print(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

}
