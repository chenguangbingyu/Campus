package com.campus.prime.ui.user;

import com.campus.prime.core.User;
import com.campus.prime.core.service.UserService;
import com.campus.prime.ui.AsyncLoader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;


public class UserProfileFragment extends Fragment
		implements LoaderCallbacks<User>{
	/**
	 * the fragment of the user
	 */
	protected User user;
	
	/**
	 * User service
	 */
	protected UserService service = new UserService();

	
	
	
	protected User load(){
		User result = null;
		int userId = ((UserActivity)getActivity()).getUserId();
		Log.i("tag",userId + " ");
		if(userId != -1)
			result = service.getProfile(userId);
		else
			result = service.getProfile();
		return result;
	}
	
	@Override
	public AsyncLoader<User> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new AsyncLoader<User>(getActivity()) {

			@Override
			protected User loadData() {
				// TODO Auto-generated method stub
				return load();
			}
		};
	}

	protected void onLoadedFinish(){
		
	}
	
	@Override
	public void onLoadFinished(Loader<User> arg0, User arg1) {
		// TODO Auto-generated method stub
		user = arg1;
		onLoadedFinish();
	}

	@Override
	public void onLoaderReset(Loader<User> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
