package com.thondph16247.nhom8.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.thondph16247.nhom8.DAO.DangKyDAO;
import com.thondph16247.nhom8.MainActivity;
import com.thondph16247.nhom8.R;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangnhap, btnDangKy;
    TextInputEditText edt_TenDn, edt_matKhau;
    CheckBox chkLuuMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btnDangnhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btn_Dangky);
        edt_matKhau = findViewById(R.id.edt_MatKhau);
        edt_TenDn = findViewById(R.id.edt_TenDangNhap);
        chkLuuMK = findViewById(R.id.chk_luumk);

        loadLoginInfo(); // Load saved login info

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDN = edt_TenDn.getText().toString();
                String mk = edt_matKhau.getText().toString();
                DangKyDAO dao = new DangKyDAO(getApplicationContext());
                boolean loggedIn = dao.checkLogin(tenDN, mk);

                if (chkLuuMK.isChecked()) {
                    saveLoginInfo(tenDN, mk);
                } else {
                    clearLoginInfo();
                }

                if (loggedIn) {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(),"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
    }

    private void loadLoginInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String tenDN = sharedPreferences.getString("tenDN", "");
        String matKhau = sharedPreferences.getString("matKhau", "");
        boolean isRemembered = sharedPreferences.getBoolean("isRemembered", false);

        edt_TenDn.setText(tenDN);
        edt_matKhau.setText(matKhau);
        chkLuuMK.setChecked(isRemembered);
    }

    private void saveLoginInfo(String tenDN, String matKhau) {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tenDN", tenDN);
        editor.putString("matKhau", matKhau);
        editor.putBoolean("isRemembered", true);
        editor.apply();
    }

    private void clearLoginInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("tenDN");
        editor.remove("matKhau");
        editor.remove("isRemembered");
        editor.apply();
    }
}
