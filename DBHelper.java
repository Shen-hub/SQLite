package com.example.playlistsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "shoppinglist.db";
    final static String TABLE_NAME = "list";
    final static String COLUMN_NAME = "columnName";
    final static String DATA = "data";
    final static String CREATE = "CREATE TABLE " + TABLE_NAME + "( `_id` INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME +" VARCHAR(255) NOT NULL, "
            + DATA + " VARCHAR(255) NOT NULL);";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // выполняется, если базы данных нет
        db.execSQL(CREATE);
        //db.execSQL("INSERT INTO playlist VALUES (1, 'Storm', 'U2', 300, 1998 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // выполняется, если версия базы данных отличается
        db.execSQL("DROP DATABASE "+DB_NAME);
        this.onCreate(db);
    }
}
