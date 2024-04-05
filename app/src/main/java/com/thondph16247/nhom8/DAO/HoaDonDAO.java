package com.thondph16247.nhom8.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thondph16247.nhom8.DTO.HoaDonDTO;
import com.thondph16247.nhom8.DTO.SanPhamDTO;
import com.thondph16247.nhom8.Databases.Dbhelper;

import java.util.ArrayList;

public class HoaDonDAO {
    SQLiteDatabase database;
    Dbhelper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new Dbhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public ArrayList<HoaDonDTO> getList(){
        ArrayList<HoaDonDTO> listHoaDon = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM tb_hoaDon ORDER BY id ASC", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                HoaDonDTO hoaDonDTO = new HoaDonDTO();
                hoaDonDTO.setId(cursor.getInt(0));
                hoaDonDTO.setTenDN(cursor.getString(1));
                hoaDonDTO.setTenSP(cursor.getString(2));
                hoaDonDTO.setSoLuong(cursor.getString(3));
                hoaDonDTO.setGiaTienMoi(cursor.getString(4)); // Thêm giá tiền mới

                listHoaDon.add(hoaDonDTO);

            } while (cursor.moveToNext());
            cursor.close();
        }
        return listHoaDon;
    }

    public int XoaHoaDon(HoaDonDTO obj){
        int  kq = database.delete("tb_hoaDon", "id="+obj.getId(),null);
        return kq;
    }
}
