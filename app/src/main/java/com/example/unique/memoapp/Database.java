package com.example.unique.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String TABLE_ITEMS = "ITEMS";
    private static final String titleData = "titleDATA";
    private static final String noteData="noteData";


    public Database(Context context) {
        super(context, "slide.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //String sql = String.format("create table %s(%s INTEGER PRIMARY KEY,%s TEXT,%s TEXT,%s INTEGER)", TABLE_ITEMS, ID, SLIDES, SLIDE_NAME, TIME_REQUIRED);
        String create_table = "CREATE TABLE " + TABLE_ITEMS + "("
                + titleData + " TEXT" +noteData+"TEXT"+")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAllItems(List<String> items, List<String> notes) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_ITEMS, null, null);


        for (int i = 0; i < items.size(); i++) {
            ContentValues values = new ContentValues();


            values.put(titleData, items.get(i));
            values.put(noteData, items.get(i));


            db.insert(TABLE_ITEMS, null, values);
        }

        Cursor c = db.rawQuery((String.format("SELECT * FROM %s", TABLE_ITEMS)), null);

        db.close();
    }

    public ArrayList<String> getAllItems() {

        List<String> items = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", TABLE_ITEMS);

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            items.add(cursor.getString(0));
        }
        db.close();
        return (ArrayList<String>) items;

    }

    public void deleteAllItems() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_ITEMS, null, null);

        db.close();

        //SetupDataActivity.items.clear();
        //SetupDataActivity.itemsSlideName.clear();
        //SetupDataActivity.itemsTimeRequired.clear();

    }

}


/*public class Database extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "JOURNAL";
    private static final String ID = "ID";
    private static final String BODY="BODY";
    private static final String TITLE = "TITLES";
    private static final String NOTE = "NOTES";
   // private static final String TIME_REQUIRED = "TIME_REQUIRED";

    public Database(Context context) {
        super(context, "memo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
              String create_table = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER," + TITLE + " TEXT," + NOTE + " TEXT"+ ")";
                 db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAllItems(List<String> items, List<String> itemsTitle) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, null, null);


        for (int i = 0; i < itemsTitle.size(); i++) {
            ContentValues values = new ContentValues();

            values.put(ID, i);
            values.put(BODY, items.get(i));
            values.put(TITLE, itemsTitle.get(i));


            db.insert(TABLE_NAME, null, values);
        }

        Cursor c = db.rawQuery((String.format("SELECT * FROM %s", TABLE_NAME)), null);

        db.close();
    }

    public void getAllItems() {

        List<String> items = new ArrayList<String>();
        List<String> itemsTitle = new ArrayList<String>();


        SQLiteDatabase db = getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", TABLE_NAME);

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            items.add(cursor.getString(1));
            itemsTitle.add(cursor.getString(2));

        }
        db.close();
        MainActivity.items.addAll(items);
       MainActivity.itemsTitle.addAll(itemsTitle);
    }

    public void deleteAllItems() {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, null, null);

        db.close();



    }

}*/
