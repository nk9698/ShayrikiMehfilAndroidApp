package com.knavic.shayribyknavic;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "Jokes";
    public static final String TABLE_ENG = "eng";
    public static final String TABLE_HINDI = "hindi";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "titel";
    public static final String DESC = "complete_jokes";

    // Database Information
    static final String DB_NAME = "shayriking.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT NOT NULL);";

    private static final String CREATE_ENG = "create table " + TABLE_ENG + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT NOT NULL);";
    private static final String CREATE_HINDI = "create table " + TABLE_HINDI + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_ENG);
        db.execSQL(CREATE_HINDI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HINDI);
        onCreate(db);
    }
}
