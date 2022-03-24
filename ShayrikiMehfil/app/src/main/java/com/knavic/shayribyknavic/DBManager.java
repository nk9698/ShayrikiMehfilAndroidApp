package com.knavic.shayribyknavic;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.knavic.shayribyknavic.DatabaseHelper.DESC;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(long id, String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper._ID, id);
        contentValue.put(DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
    public void eng_insert(long id, String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper._ID, id);
        contentValue.put(DESC, desc);
        database.insert(DatabaseHelper.TABLE_ENG, null, contentValue);
    }
    public void hindi_insert(long id, String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper._ID, id);
        contentValue.put(DESC, desc);
        database.insert(DatabaseHelper.TABLE_HINDI, null, contentValue);
    }

    public int getid(){
        Cursor cursor = database.rawQuery("SELECT MAX("+DatabaseHelper._ID+") FROM "+DatabaseHelper.TABLE_NAME, null);
        return (cursor.moveToFirst() ? cursor.getInt(0) : 0);

    }
    public int eng_getid(){
        Cursor cursor = database.rawQuery("SELECT MAX("+DatabaseHelper._ID+") FROM "+DatabaseHelper.TABLE_ENG, null);
        return (cursor.moveToFirst() ? cursor.getInt(0) : 0);

    }
    public int hindi_getid(){
        Cursor cursor = database.rawQuery("SELECT MAX("+DatabaseHelper._ID+") FROM "+DatabaseHelper.TABLE_HINDI, null);
        return (cursor.moveToFirst() ? cursor.getInt(0) : 0);

    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor eng_fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_ENG, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor hindi_fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DESC };
        Cursor cursor = database.query(DatabaseHelper.TABLE_HINDI, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public String getjoke(String id){
        Cursor cursor=database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper._ID+"="+id+"", null);
        cursor.moveToFirst();
        int desc = cursor.getColumnIndex(DESC);
        String desc1 = cursor.getString(desc);
        return desc1;
    }
    public String eng_getjoke(String id){
        Cursor cursor=database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_ENG+" WHERE "+DatabaseHelper._ID+"="+id+"", null);
        cursor.moveToFirst();
        int desc = cursor.getColumnIndex(DESC);
        String desc1 = cursor.getString(desc);
        return desc1;
    }
    public String hindi_getjoke(String id){
        Cursor cursor=database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_HINDI+" WHERE "+DatabaseHelper._ID+"="+id+"", null);
        cursor.moveToFirst();
        int desc = cursor.getColumnIndex(DESC);
        String desc1 = cursor.getString(desc);
        return desc1;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public void deleteall(){
        database.execSQL("delete from "+ DatabaseHelper.TABLE_NAME);
    }
    public void eng_deleteall(){
        database.execSQL("delete from "+ DatabaseHelper.TABLE_ENG);
    }
    public void hindi_deleteall(){
        database.execSQL("delete from "+ DatabaseHelper.TABLE_HINDI);
    }

}
