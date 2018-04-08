package com.example.mappe2h13_s169537;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBAdapter {
	Context context;
	static final String TAG = "DbHelper";
	static final String DB_NAVN = "contacts.db";
	static final String TABELL = "contacts";
	static final String ID = BaseColumns._ID;
	static final String FIRSTNAME = "firstName";
	static final String LASTNAME = "lastName";
	static final String PHONE = "phoneNumber";
	static final String BIRTHDAY = "birthday";
	static final String MESSAGE = "message";

	static final int DB_VERSJON = 4;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DB_NAVN, null, DB_VERSJON);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table " + TABELL + " (" + ID
					+ " integer primary key autoincrement, " + FIRSTNAME
					+ " text, " + LASTNAME + " text, " + PHONE + " text, "
					+ BIRTHDAY + " text, " + MESSAGE + " text);";
			Log.d(TAG, "oncreated sql" + sql);
			db.execSQL(sql);
		}

		// gammelversjon, nyversjon
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + TABELL);
			Log.d(TAG, "updated");
			onCreate(db);

		}
	}

	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		// DBHelper.onUpgrade( db, 4, 1 );
		return this;
	}

	public void close() {
	}

	// legger inn i databasen
	public void insert(ContentValues cv) {
		db.insert(TABELL, null, cv);
	}

	// Sletter kontakt med gitt telefonnr
	public boolean delete(String phone) {
		return db.delete(TABELL, PHONE + "='" + phone + "'", null) > 0;
	}

	// oppdaterer databasen
	public boolean update(String firstName, String lastName, String phone,
			String birthday, String message) {
		ContentValues cv = new ContentValues(5);
		cv.put(FIRSTNAME, firstName);
		cv.put(LASTNAME, lastName);
		cv.put(PHONE, phone);
		cv.put(BIRTHDAY, birthday);
		cv.put(MESSAGE, message);
		return db.update(TABELL, cv, PHONE + "='" + phone + "'", null) > 0;
	}

	// Finner alle kontaktene
	public Cursor FindAll() {
		Cursor cur;
		String[] cols = { ID, FIRSTNAME, LASTNAME, PHONE, BIRTHDAY, MESSAGE };
		cur = db.query(TABELL, cols, null, null, null, null, BIRTHDAY);
		return cur;
	}

	// Finner bruker kan brukes til search
	public Cursor FindUser(String phone) {
		Cursor cur;
		String[] cols = { ID, FIRSTNAME, LASTNAME, PHONE, BIRTHDAY, MESSAGE }; // MESSAGE};
		cur = db.query(TABELL, cols, PHONE + "='" + phone + "'", null, null,
				null, null);
		return cur;
	}

	// Finner bursdag
	public Cursor FindBirthday(String birthday) {
		Cursor cur;
		String[] cols = { ID, FIRSTNAME, LASTNAME, PHONE, BIRTHDAY, MESSAGE }; // MESSAGE};
		cur = db.query(TABELL, cols, BIRTHDAY + "='" + birthday + "'", null,
				null, null, null);
		return cur;
	}

}
