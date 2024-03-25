package com.thondph16247.nhom8.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thondph16247.nhom8.DTO.DangKyDTO;
import com.thondph16247.nhom8.Databases.Dbhelper;

public class DangKyDAO {
    SQLiteDatabase database;
    Dbhelper dbHelper;

    public DangKyDAO(Context context) {
        dbHelper = new Dbhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long Them(DangKyDTO dto) {
        ContentValues values = new ContentValues();
        values.put("tenDN", dto.getTenDN());
        values.put("matKhau", dto.getMatKhau());
        long kq = database.insert("tb_dky", null, values);
        return kq;
    }

    // Thêm phương thức để lấy thông tin đăng nhập từ cơ sở dữ liệu
    public boolean checkLogin(String tenDN, String mk) {
        String[] columns = {"id"};

        String selection = "tenDN = ? AND matKhau = ?";
        String[] selectionArgs = {tenDN, mk};

        Cursor cursor = database.query("tb_dky", columns, selection, selectionArgs, null, null, null);

        boolean loginSuccessful = cursor != null && cursor.moveToFirst();

        if (cursor != null) {
            cursor.close();
        }

        return loginSuccessful;
    }
    


}
