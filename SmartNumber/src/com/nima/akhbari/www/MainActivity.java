package com.nima.akhbari.www;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity implements NewNumberHandle,
		PhoneListHandle {
	Fragment currentFragment;
	public FragmentCommunicator fragmentCommunicator;
	Button bNewNumber;
	EditText etNewNumber;
	public List<String> array = new ArrayList<String>();
	public List<String> phoneList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setDropDownBar();
	}

	/**
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 *           menu; this adds items to the action bar if it is present.
	 *           getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

	// Set-up for the Action Bar Drop Down Menu
	public void setDropDownBar() {
		final ActionBar mBar = getActionBar();
		array.add("Add New Number");
		// Removes title
		mBar.setDisplayShowTitleEnabled(false);
		new ActionBar.LayoutParams(Gravity.RIGHT);
		// adds a list navigation method on the actionbar
		mBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		SpinnerAdapter mSpinnerAdapter = new ArrayAdapter<String>(this,
				R.layout.dropdown, array);
		// Listens for events
		ActionBar.OnNavigationListener mOnNavigationListener = new ActionBar.OnNavigationListener() {

			public boolean onNavigationItemSelected(int position, long itemId) {
				Log.d("Drop Down position", Integer.toString(position));
				Log.d("Drop Down itemId", Long.toString(itemId));

				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				if (position == 0) {
					currentFragment = new NewNumberFragment();
					ft.replace(R.id.myFragment, currentFragment);
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					ft.addToBackStack(null);
					ft.commit();
				} else {
					currentFragment = new PhoneListFragment();
					ft.replace(R.id.myFragment, currentFragment);
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					ft.addToBackStack(null);
					ft.commit();

					phoneList.add("1-800-555-5555");
					if (fragmentCommunicator != null) {

						fragmentCommunicator.passDataToFragment(phoneList);
					} else {

						Log.d("fragmentCommunicator is null",
								"fragmentCommunicator == null");
					}
				}

				return true;

			}
		};
		// Set the adapter and navigation callback for list navigation mode.
		mBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
	}

	@Override
	public void passDataToActivity(String phoneNumber) {
		// TODO Auto-generated method stub
		if (!array.contains(phoneNumber)) {
			array.add(phoneNumber);
		}
		Log.i("phoneNumber", "The Phone Number is " + phoneNumber);
	}

	@Override
	public void passDataToActivity(boolean ready) {
		// TODO Auto-generated method stub
		if (fragmentCommunicator != null) {

			fragmentCommunicator.passDataToFragment(phoneList);
		} else {

			Log.d("fragmentCommunicator is null",
					"fragmentCommunicator == null");
		}
	}
}
