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

public class MainActivity extends Activity implements ActivityCommunicator {
	Fragment currentFragment;
	Button bNewNumber;
	EditText etNewNumber;
	public List<String> array = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		currentFragment = new NewNumberFragment();
		ft.add(R.id.myFragment, currentFragment);

		ft.commit();

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
				array.add(Integer.toString(position));
				return true;

			}
		};
		// Set the adapter and navigation callback for list navigation mode.
		mBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
	}

	@Override
	public void passDataToActivity(String phoneNumber) {
		// TODO Auto-generated method stub

		Log.i("phoneNumber", "The Phone Number is " + phoneNumber);
	}
}
