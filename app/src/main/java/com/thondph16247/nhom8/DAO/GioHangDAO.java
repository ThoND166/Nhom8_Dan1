package com.thondph16247.nhom8.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thondph16247.nhom8.DTO.GioHangDTO;
import com.thondph16247.nhom8.Databases.Dbhelper;

import java.util.ArrayList;

public class GioHangDAO {

    SQLiteDatabase database;
    Dbhelper dbHelper;

    public GioHangDAO(Context context) {
        dbHelper = new Dbhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public ArrayList<GioHangDTO> getList(){
        ArrayList<GioHangDTO> listGioHang = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tb_gioHang ORDER BY id ASC", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                GioHangDTO gioHangDTO = new GioHangDTO();
                gioHangDTO.setId(cursor.getInt(0));
                gioHangDTO.setTenSP(cursor.getString(1));
                gioHangDTO.setGiaTien(cursor.getString(2));
                gioHangDTO.setSoLuongGioHang(cursor.getString(3));
                gioHangDTO.setGiaTienMoi(cursor.getString(4)); // Thêm giá tiền mới

                listGioHang.add(gioHangDTO);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return listGioHang;
    }

    public long ThemGioHang(GioHangDTO obj){
        // Tính toán giaTienMoi
        int giaTien = Integer.parseInt(obj.getGiaTien());
        int soLuong = Integer.parseInt(obj.getSoLuongGioHang());
        int giaTienMoi = giaTien * soLuong;

        ContentValues values = new ContentValues();
        values.put("tenSP", obj.getTenSP());
        values.put("giaTien", obj.getGiaTien());
        values.put("soLuongGioHang", obj.getSoLuongGioHang());
        values.put("giaTienMoi", String.valueOf(giaTienMoi)); // Lưu giá tiền mới

        long kq = database.insert("tb_gioHang",null,values);
        return kq;
    }

    public int XoaGioHang(GioHangDTO obj){
        int  kq = database.delete("tb_gioHang", "id="+obj.getId(),null);
        return kq;
    }

    public long CapNhatGiaTienMoi(GioHangDTO obj) {
        // Tính toán giaTienMoi
        int giaTien = Integer.parseInt(obj.getGiaTien());
        int soLuong = Integer.parseInt(obj.getSoLuongGioHang());
        int giaTienMoi = giaTien * soLuong;

        ContentValues values = new ContentValues();
        values.put("giaTienMoi", String.valueOf(giaTienMoi));

        long kq = database.update("tb_gioHang", values, "id=?", new String[]{String.valueOf(obj.getId())});
        return kq;
    }
}
