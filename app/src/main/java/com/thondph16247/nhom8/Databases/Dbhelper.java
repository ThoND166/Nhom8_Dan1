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
        // Đăng ký, đăng nhập nhé
        db.execSQL("CREATE TABLE tb_dky (id INTEGER PRIMARY KEY AUTOINCREMENT, tenDN TEXT NOT NULL, matKhau TEXT NOT NULL)");
        db.execSQL("INSERT INTO tb_dky (tenDN, matKhau) VALUES ('admin', 'admin')");
        db.execSQL("INSERT INTO tb_dky (tenDN, matKhau) VALUES ('tho', '1')");


        // loại trái cây
        String sql_loaiTraiCay = "CREATE TABLE tb_loaiTraiCay (id INTEGER PRIMARY KEY AUTOINCREMENT, tenLoai TEXT NOT NULL)";
        db.execSQL(sql_loaiTraiCay);

        String sql_insertData1 = "INSERT INTO tb_loaiTraiCay (tenLoai) VALUES ('Nho')";
        String sql_insertData2 = "INSERT INTO tb_loaiTraiCay (tenLoai) VALUES ('Táo')";

        db.execSQL(sql_insertData1);
        db.execSQL(sql_insertData2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
