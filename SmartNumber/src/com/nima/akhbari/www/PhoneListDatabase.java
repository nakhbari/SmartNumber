package com.nima.akhbari.www;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class PhoneListDatabase extends SQLiteOpenHelper {

	private static final String TAG = PhoneListDatabase.class.getSimpleName();
	public static final String DB_NAME = "phoneList.db";
	public static final int DB_VERSION = 1;
	public static final String TABLE = "PhoneList";
	public static final String C_ID = BaseColumns._ID; // Special for id
	public static final String C_NUMBER = "PhoneNumber";
	public static final String C_IS_IN_DICTIONARY = "IsInDictionary";
	public static final String C_IS_IN_DROPDOWN = "IsInDropdown";
	public static final String C_CONVERTED_NUMBER = "ConvertedNumber";

	public PhoneListDatabase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = String
				.format("CREATE TABLE %s (%s INT, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR);",
						TABLE, C_ID, C_NUMBER, C_IS_IN_DICTIONARY,
						C_IS_IN_DROPDOWN, C_CONVERTED_NUMBER);
		Log.d(TAG,"onCreate sql: "+ sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE);
		Log.d(TAG,"onUpdate dropped table "+ TABLE);
		this.onCreate(db);
	}

}
