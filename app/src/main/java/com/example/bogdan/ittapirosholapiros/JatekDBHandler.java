package com.example.bogdan.ittapirosholapiros;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by Vadek on 2018.01.04..
 */

public class JatekDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pontszamok.db";
    private static final String TABLE_PONTSZAMOK = "pontszamok";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PONTSZAM = "_eredmeny";

    public JatekDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "
                + TABLE_PONTSZAMOK
                + "("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PONTSZAM
                + " TEXT "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PONTSZAMOK);
        onCreate(db);
    }

    public void addPontszam(Pontszamok pontszamok){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PONTSZAM, pontszamok.get_eredmeny());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PONTSZAMOK, null, values);
        db.close();
    }

    public String adatbazisToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "
                + TABLE_PONTSZAMOK
                + " WHERE 1";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_eredmeny")) != null) {
                dbString += c.getString(c.getColumnIndex("_eredmeny"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
