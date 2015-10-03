package com.bankrate.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.bankrate.common.Common;

/**
 * @Description: lop SQLiteOpenHelper trien khai thuc hien thao tac voi database
 * @author:truonglt2
 * @since:Feb 7, 2014 4:12:01 PM
 * @version: 1.0
 * @since: 1.0
 * 
 */
public class DatabaseHelp extends SQLiteOpenHelper {
	protected static DatabaseHelp instance;
	Common common = new Common();
	private SQLiteDatabase mDB;

	// "/mnt/sdcard/";
	// public static String SDCARD_PATH =
	// Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
	public static String SDCARD_PATH = "data/data/" + Common.PACKAGE_NAME
			+ "/databases/";
	private static final String DATABASE_NAME = "bookDatabase.sqlite";
	private static Context mContext;

	/**
	 * Khoi tao the hien cua lop DatabaseHelp
	 * 
	 * @author: truonglt2
	 * @param context
	 * @return
	 * @return: DatabaseHelp
	 * @throws:
	 */
	public static DatabaseHelp getInstance(Context context) {

		if (instance == null) {
			instance = new DatabaseHelp(context);
		}
		return instance;
	}

	/**
	 * @param context
	 */
	public DatabaseHelp(Context context) {
		super(context, DATABASE_NAME, null, 1);
		mContext = context;
	}

	/**
	 * Tao database
	 * 
	 * @author: truonglt2
	 * @throws IOException
	 * @return: void
	 * @throws:
	 */
	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			// do nothing if database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * kiem tra da co database chua
	 * 
	 * @author: truonglt2
	 * @return
	 * @return: boolean
	 * @throws:
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			;
			String myPath = SDCARD_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}

	/**
	 * Copy database tu assets sang thu muc chua database
	 * 
	 * @author: truonglt2
	 * @throws IOException
	 * @return: void
	 * @throws:
	 */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = mContext.getAssets().open(DATABASE_NAME);
		// Path to the just created empty db
		// String outFileName = DB_PATH + DATABASE_NAME;
		String outFileName = SDCARD_PATH + DATABASE_NAME;
		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;

		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	/**
	 * Mo ket noi den database
	 * 
	 * @author: truonglt2
	 * @throws SQLException
	 * @return: void
	 * @throws:
	 */
	public void openDataBase() throws SQLException {
		// Open the database
		String myPath = SDCARD_PATH + DATABASE_NAME;
		System.out.println("myPath:" + myPath);
		try {
			mDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Dong ket noi
	 * 
	 * @author: truonglt2
	 * @return: DatabaseHelp
	 * @throws:
	 */
	@Override
	public synchronized void close() {
		if (mDB != null)
			mDB.close();
		super.close();
	}

	/**
	 * on create lop SQLiteDatabase
	 * 
	 * @author: truonglt2
	 * @param db
	 * @return: DatabaseHelp
	 * @throws:
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	/**
	 * update database
	 * 
	 * @author: truonglt2
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 * @return: DatabaseHelp
	 * @throws:
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * Thuc hien execute query tra ve ket qua
	 * 
	 * @author: truonglt2
	 * @param query
	 * @return: void
	 * @throws:
	 */
	public void executeQuery(String query) {
		try {
			if (mDB.isOpen()) {
				mDB.close();
			} // end if
			openDataBase();
			mDB.execSQL(query);
		} catch (Exception e) {
			System.out.println("DATABASE ERROR ! !" + e);
		} finally {
			close();
		}
	}

	/**
	 * Thuc hien raw query
	 * 
	 * @author: truonglt2
	 * @param query
	 * @return
	 * @return: Cursor
	 * @throws:
	 */
	public Cursor selectQuery(String query) {
		Cursor c1 = null;
		try {
			openDataBase();
			c1 = mDB.rawQuery(query, null);
		} catch (Exception e) {
			System.out.println("DATABASE ERROR ! !");
		}
		return c1;
	}

}
