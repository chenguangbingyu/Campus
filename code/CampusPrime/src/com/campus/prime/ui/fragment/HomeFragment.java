package com.campus.prime.ui.fragment;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;

import com.campus.prime.R;
import com.campus.prime.adapter.MessageListViewAdapter;
import com.campus.prime.adapter.SingleTypeAdapter;
import com.campus.prime.bean.MessageItem;
import com.campus.prime.bean.MessagePage;
import com.campus.prime.https.MessageService;

public class HomeFragment extends MessagePageFragment{
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		setEmptyText("No Message");
	}
	
	@Override
	public void onViewStateRestored(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewStateRestored(null);
	}
	
}
