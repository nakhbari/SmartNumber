package com.nima.akhbari.www;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PhoneListFragment extends ListFragment implements
		FragmentCommunicator {

	public List<String> array = new ArrayList<String>();
	PhoneListHandle phoneListHandle;
	Context context;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = getActivity();
		try{
			phoneListHandle = (PhoneListHandle)context;
		}catch(ClassCastException e){
			Log.i("Handler Exception", "The Activity "+ context.getClass().getSimpleName() + " does not implement PhoneListHandle interface" );
		}
		((MainActivity) getActivity()).fragmentCommunicator = this;
		phoneListHandle.passDataToActivity(true);
		Log.d("onAttach PhoneListFragment", "onAttach PhoneListFragment is working");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, array));

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void passDataToFragment(List<String> list) {
		// TODO Auto-generated method stub
		array = list;
	}

}
