package com.nima.akhbari.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

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
				} else {
					currentFragment = new PhoneListFragment();
					// Open Database
					PhoneListDatabase phoneListDatabase = new PhoneListDatabase(
							MainActivity.this);
					SQLiteDatabase db = phoneListDatabase.getReadableDatabase();

					Cursor cursor = db.rawQuery(
							"SELECT " + PhoneListDatabase.C_CONVERTED_NUMBER
									+ " FROM " + PhoneListDatabase.TABLE
									+ " WHERE " + PhoneListDatabase.C_NUMBER
									+ " = '" + array.get(position) + "'", null);

					phoneList.clear();
					for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
							.moveToNext()) {
						phoneList
								.add(cursor.getString(cursor
										.getColumnIndex(PhoneListDatabase.C_CONVERTED_NUMBER)));
					}

					// Close Database
					db.close();
					phoneListDatabase.close();

				}

				ft.replace(R.id.myFragment, currentFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.addToBackStack(null);
				ft.commit();
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
		// Open Database
		PhoneListDatabase phoneListDatabase = new PhoneListDatabase(
				MainActivity.this);
		SQLiteDatabase db = phoneListDatabase.getWritableDatabase();

		// Create Content Value
		ContentValues values = new ContentValues();
		values.put(PhoneListDatabase.C_NUMBER, phoneNumber);
		values.put(PhoneListDatabase.C_IS_IN_DICTIONARY, "False");
		values.put(PhoneListDatabase.C_IS_IN_DROPDOWN, "True");
		values.put(PhoneListDatabase.C_CONVERTED_NUMBER, "Blah blah");

		// Insert into database
		db.insert(PhoneListDatabase.TABLE, null, values);

		// Close Database
		db.close();
		phoneListDatabase.close();

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
