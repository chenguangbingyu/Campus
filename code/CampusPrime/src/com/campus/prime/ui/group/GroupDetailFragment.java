package com.campus.prime.ui.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.campus.prime.core.Group;
import com.campus.prime.core.service.GroupService;
import com.campus.prime.ui.AsyncLoader;


public class GroupDetailFragment extends Fragment
		implements LoaderCallbacks<Group>{
	/**
	 * the fragment of the group
	 */
	protected Group group;
	
	/**
	 * Group Service
	 */
	protected GroupService service = new GroupService();

	
	
	protected Group load(){
		return service.getDetail(((GroupActivity)this.getActivity()).getGroupId());
	}
	
	@Override
	public Loader<Group> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return new AsyncLoader<Group>(getActivity()) {

			@Override
			protected Group loadData() {
				// TODO Auto-generated method stub
				return load();
			}
		};
	}

	@Override
	public void onLoadFinished(Loader<Group> arg0, Group arg1) {
		// TODO Auto-generated method stub
		group = arg1;
		onLoadedFinish();
	}

	
	protected void onLoadedFinish(){
		
	}
	
	@Override
	public void onLoaderReset(Loader<Group> arg0) {
		// TODO Auto-generated method stub
		
		
	}
	
	
}
