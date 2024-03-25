package com.thondph16247.nhom8.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public Dbhelper( Context context) {
        super(context, "duan1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_dky (\n" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tenDN     TEXT    NOT NULL,\n" +
                "    matKhau    TEXT    NOT NULL" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
