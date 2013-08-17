package com.nima.akhbari.www;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ActionBar mBar = getActionBar();
		// adds a list navigation method on the actionbar
		mBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.action_list,
				android.R.layout.simple_spinner_dropdown_item);
		// Listens for events
		ActionBar.OnNavigationListener mOnNavigationListener = new ActionBar.OnNavigationListener() {

			public boolean onNavigationItemSelected(int position, long itemId) {
				Log.d("Drop Down position", Integer.toString(position));
				Log.d("Drop Down itemId", Long.toString(itemId));
				return true;

			}

		};
		// Set the adapter and navigation callback for list navigation mode.
		mBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
	}

	/**
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 *           menu; this adds items to the action bar if it is present.
	 *           getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

}
