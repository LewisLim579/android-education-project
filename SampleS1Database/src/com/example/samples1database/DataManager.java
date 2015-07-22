package com.example.samples1database;

import java.util.ArrayList;
import java.util.List;

import com.example.samples1database.DBConstant.PersonTable;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataManager extends SQLiteOpenHelper implements DBConstant.PersonTable {

	private static DataManager instance;
	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}
	
	private static final String DB_NAME = "person";
	private static final int DB_VERSION = 1;
	private DataManager() {
		super(MyApplication.getContext(), DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "+TABLE_NAME+"(" + 
					_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
					NAME+" TEXT NOT NULL," +
					AGE+" INTEGER);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public boolean addPerson(Person person) {
		SQLiteDatabase db = getWritableDatabase();
//		String sql = "INSERT INTO persontbl(name,age) values('" + person.name + "'," + person.age + ");";
//		db.execSQL(sql);
		ContentValues values = new ContentValues();
		values.put(DBConstant.PersonTable.NAME, person.name);
		values.put(DBConstant.PersonTable.AGE, person.age);
		db.insert(PersonTable.TABLE_NAME, null, values);
		return true;
	}
	
	public List<Person> getPersonList(String keyword) {
		List<Person> list = new ArrayList<Person>();
		Cursor c = getPersonCursor(keyword);
		
		while(c.moveToNext()) {
			Person p = new Person();
			p.id = c.getLong(c.getColumnIndex(PersonTable._ID));
			p.name = c.getString(c.getColumnIndex(PersonTable.NAME));
			p.age = c.getInt(c.getColumnIndex(PersonTable.AGE));
			list.add(p);
		}
		
		return list;
	}
	
	public Cursor getPersonCursor(String keyword) {
		SQLiteDatabase db = getReadableDatabase();
//		String sql = "SELECT _id, name, age FROM persontbl";
//		if (keyword != null && !keyword.equals("")) {
//			sql = sql + " WHERE name = '" + keyword + "'";
//		}
//		Cursor c = db.rawQuery(sql, null);
		String[] columns = {PersonTable._ID, PersonTable.NAME, PersonTable.AGE};
		String selection = null;
		String[] selectionArgs = null;
		if (keyword != null && !keyword.equals("")) {
			selection = PersonTable.NAME + " = ?";
			selectionArgs = new String[]{ keyword };
		}
		String groupBy = null;
		String having = null;
		String orderBy = null;
		return db.query(PersonTable.TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
	}

}
