package com.campus.prime.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * 所有activity的父类
 * @author absurd
 *
 */
public class BaseActivity extends ActionBarActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		final ActionBar actionBar = getSupportActionBar();
		configureActionBar(actionBar);
	}
	
	private void configureActionBar(ActionBar actionBar){
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setIcon(null);
	}
}
