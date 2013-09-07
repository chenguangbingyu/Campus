package com.campus.prime.ui;

import com.campus.prime.R;
import com.campus.prime.R.layout;
import com.campus.prime.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MessageDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_detail, menu);
		return true;
	}

}
